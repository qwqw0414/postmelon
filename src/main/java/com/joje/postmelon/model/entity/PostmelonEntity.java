package com.joje.postmelon.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "tb_postmelon")
public class PostmelonEntity {

    @Id
    private Long id;

    private String title;
    private String artist;
    private String lyrics;
    private String album;
    private String albumArt;
    private String detailUrl;
    private String genre;
    private String releaseDate;

}
