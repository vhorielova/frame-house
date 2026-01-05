package com.fcsc.pi.framehouse.service;

import com.fcsc.pi.framehouse.dto.CreateFilmRequest;
import com.fcsc.pi.framehouse.models.Film;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FilmService {
    void addFilm(CreateFilmRequest createFilmRequest, MultipartFile file);

    public List<Film> searchFilmByTitle(String title);
    List<Film> searchFilmByTitle(String title, boolean withoutDescriptions);

}
