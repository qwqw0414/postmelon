package com.joje.postmelon.service;

import com.joje.postmelon.model.dto.PostmelonDto;
import com.joje.postmelon.model.dto.SongDto;

public interface PostMelonService {
    String getSongIdByKeyword(String keyword);

    PostmelonDto getSongById(String songId);

    boolean insertPostmelon(PostmelonDto postmelonDto);
}
