package com.joje.postmelon.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "tb_postmelon")
public class PostmelonEntity {


    @Id
    private String id;

    private String title;
    private String artist;
    private String lyrics;

}
