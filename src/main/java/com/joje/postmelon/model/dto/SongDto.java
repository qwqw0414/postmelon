package com.joje.postmelon.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SongDto {
    private String songId;
    private String songName;
    private String lyrics;

    private ArtistDto artist;
}
