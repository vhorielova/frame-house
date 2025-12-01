package com.fcsc.pi.framehouse.service;

import com.fcsc.pi.framehouse.dto.CreateFilmRequest;
import com.fcsc.pi.framehouse.exceptions.GenreNotFoundException;
import com.fcsc.pi.framehouse.models.Film;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

public interface FilmService {
    void addFilm(CreateFilmRequest createFilmRequest, MultipartFile file);
}
