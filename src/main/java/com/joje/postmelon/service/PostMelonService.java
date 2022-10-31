package com.joje.postmelon.service;

import com.joje.postmelon.model.dto.SongDto;

public interface PostMelonService {
    String getSongIdByKeyword(String keyword);

    SongDto getSongById(String songId);
}
