package com.joje.postmelon.service.impl;

import com.joje.postmelon.component.HttpRequestComponent;
import com.joje.postmelon.model.dto.PostmelonDto;
import com.joje.postmelon.model.entity.PostmelonEntity;
import com.joje.postmelon.repository.PostmelonRepository;
import com.joje.postmelon.service.PostMelonService;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service("PostMelonService")
public class PostMelonServiceImpl implements PostMelonService {

    @Autowired
    private HttpRequestComponent httpRequestComponent;

    @Autowired
    private PostmelonRepository postmelonRepository;

    private final String SEARCH_URI;
    private final String DETAIL_URI;

    public PostMelonServiceImpl(@Value("${postmelon.uri.search}") String search,
                                @Value("${postmelon.uri.detail}") String detail) {
        this.SEARCH_URI = search;
        this.DETAIL_URI = detail;
    }

    @Override
    public String getSongIdByKeyword(String keyword) {
        String songId = "";
//        HTML 로드
        Document html = httpRequestComponent.requestHtml(SEARCH_URI + keyword);

//        아이디값 크롤링
        Elements tables = html.select("#frm_songList table");
//        log.debug("[tables]=[{}]", tables);

//        해당 검색 테이블이 없을 경우 우회
        if(tables.html().length() < 1){
            tables = html.select("#frm_searchSong table");
//            log.debug("[tables]=[{}]", tables);
        }

        Elements a = tables.eq(0).select("td.t_left a");
//        log.debug("[a]=[{}]", a);

//        href에서 아이디값 조회
        if(a.html().length() > 0) {
            Matcher matcher = Pattern.compile("\\d+").matcher(a.attr("href"));
            if(matcher.find()){
                songId = matcher.group();
            }
        }
        return songId;
    }

    @Override
    public PostmelonDto getSongById(String songId) {

        Document html = httpRequestComponent.requestHtml(DETAIL_URI + songId);
        Elements elmts = html.select("div.entry dl.list dd");

        String album = elmts.eq(0).select("a").text();
        String releaseDate = elmts.eq(1).text();
        String genre = elmts.eq(2).text();

        String albumArt = html.select("div.section_info img").attr("src");

        return PostmelonDto.builder()
                .id(Long.parseLong(songId))
                .title(this.getSongNameByMelon(html))
                .artist(this.getArtistByMelon(html))
                .album(album)
                .genre(genre)
                .albumArt(albumArt)
                .detailUrl(DETAIL_URI + songId)
                .lyrics(StringUtil.join(this.getLyricsToMelon(html), "\n"))
                .releaseDate(releaseDate.replaceAll("\\.", "-"))
                .build();
    }

    @Override
    public boolean insertPostmelon(PostmelonDto postmelonDto) {
        long count = postmelonRepository.countById(postmelonDto.getId());
        if(count == 0) {
            PostmelonEntity postmelonEntity = PostmelonEntity.builder()
                    .id(postmelonDto.getId())
                    .title(postmelonDto.getTitle())
                    .artist(postmelonDto.getArtist())
                    .lyrics(postmelonDto.getLyrics())
                    .album(postmelonDto.getAlbum())
                    .albumArt(postmelonDto.getAlbumArt())
                    .genre(postmelonDto.getGenre())
                    .detailUrl(postmelonDto.getDetailUrl())
                    .releaseDate(postmelonDto.getReleaseDate())
                    .build();
            postmelonRepository.save(postmelonEntity);
            return true;
        }
        else {
            return false;
        }
    }

    private List<String> getLyricsToMelon(Document doc) {
        String FILTER_STR = "<!-- height:auto; 로 변경시, 확장됨 -->";
        List<String> lyrics = new ArrayList<>();
        Element elmt = doc.selectFirst("div.lyric");
        if (elmt != null) {
            String html = elmt.html().replace(FILTER_STR, "");
            String[] line = html.split("<br>");
            for (String l : line) {
                lyrics.add(l.trim());
            }
        }
        return lyrics;
    }


    private String getSongNameByMelon(Document doc) {
        Element elmt = doc.selectFirst("div.song_name");
        String songTitle = "";
        if (elmt != null) {
            songTitle = elmt.text().replace("곡명", "").trim();
        }
        return songTitle;
    }

    private String getArtistByMelon(Document doc) {
        Element elmt = doc.selectFirst("div.artist");
        return elmt.selectFirst("span").text().trim();
    }
}
