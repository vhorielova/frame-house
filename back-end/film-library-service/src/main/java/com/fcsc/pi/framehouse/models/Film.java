package com.fcsc.pi.framehouse.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    private String posterFilename;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "film_genre",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genres;
}
