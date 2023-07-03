package com.joje.postmelon.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostmelonDto {
    private Long id;
    private String title;
    private String artist;
    private String lyrics;
    private String album;
    private String albumArt;
    private String genre;
    private String releaseDate;
}
