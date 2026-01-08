package com.fcsc.pi.framehouse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilmCatalogResponse {
    private int id;
    private String title;
    private String director;
    private String company;
    private String posterFilename;
    private List<String> genres;
}
