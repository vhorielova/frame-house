package com.fcsc.pi.framehouse.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name= "film")
public class Film extends BaseEntity {
    @Column(name = "title")
    private String title;

    @Column (name = "description")
    private String desctiprion;

    @Column (name = "director")
    private String director;

    @Column (name = "company")
    private String company;

    @Column (name = "image_filepath")
    private String imageUrl;

    @Column (name = "genre_id")
    private int genreId;
}
