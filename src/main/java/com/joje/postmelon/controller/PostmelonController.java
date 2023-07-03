package com.joje.postmelon.controller;

import com.joje.postmelon.exception.NotFoundDataException;
import com.joje.postmelon.model.dto.SongDto;
import com.joje.postmelon.model.vo.ResultVo;
import com.joje.postmelon.service.PostMelonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostmelonController {

    private final PostMelonService postMelonService;

    @GetMapping(value = "/search/{keyword}", produces = "application/json; charset=utf8")
    public ResponseEntity<?> search(@PathVariable("keyword") String keyword) throws Exception {

        log.info("[keyword]=[{}]", keyword);
        String id = postMelonService.getSongIdByKeyword(keyword);
        ResultVo resultVo = new ResultVo();

        if(id != null && id.length() > 0){
            SongDto song = postMelonService.getSongById(id);

//            log.info("[title]=[{}], [artist]=[{}]", song.getSongName(), song.getArtist().getArtistId());
//            log.debug("[song]=[{}]", song);

            boolean isInsert = postMelonService.insertPostmelon(song);

            resultVo.put("target", song);
            return new ResponseEntity<>(resultVo, HttpStatus.OK);
        }else {
            resultVo.setStatus(false);
            return new ResponseEntity<>(resultVo, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/error/{type}", produces = "application/json; charset=utf8")
    public ResponseEntity<?> error(@PathVariable("type") String type) throws Exception {
        log.debug("[type]=[{}]", type);
        if ("RunTime".equals(type)) {
            throw new RuntimeException();
        } else if ("NotFoundData".equals(type)) {
            throw new NotFoundDataException();
        } else if ("NullPoint".equals(type)) {
            throw new NullPointerException();
        }

        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
