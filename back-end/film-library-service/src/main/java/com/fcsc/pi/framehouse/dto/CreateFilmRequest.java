package com.fcsc.pi.framehouse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CreateFilmRequest {
    private String title;
    private String description;
    private String director;
    private String company;
    private List<String> genres;
}
