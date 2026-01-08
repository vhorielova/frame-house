package com.fcsc.pi.framehouse.service.implementation;

import com.fcsc.pi.framehouse.dto.CreateFilmRequest;
import com.fcsc.pi.framehouse.dto.FilmCatalogResponse;
import com.fcsc.pi.framehouse.exceptions.GenreNotFoundException;
import com.fcsc.pi.framehouse.models.Film;
import com.fcsc.pi.framehouse.models.Genre;
import com.fcsc.pi.framehouse.repository.FilmRepository;
import com.fcsc.pi.framehouse.service.FilmService;
import com.fcsc.pi.framehouse.service.GenreService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;
    private final GenreService genreService;
    private final S3StorageService s3StorageService;

    /**
     * Saves film, saves the file, generation a new unique name for it
     */
    @Override
    public void addFilm(CreateFilmRequest createFilmRequest, MultipartFile file) {

        Film film = new Film();
        film.setTitle(createFilmRequest.getTitle());
        film.setDesctiprion(createFilmRequest.getDescription());
        film.setDirector(createFilmRequest.getDirector());
        film.setCompany(createFilmRequest.getCompany());

        // Saving the file and it's path
        try {
            String posterFilename = s3StorageService.generateNameAndSave(file, createFilmRequest.getTitle());
            film.setPosterFilename(posterFilename);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Creating genres if necessary
        List<Genre> genres = mapNamesToGenres(createFilmRequest.getGenres());
        film.setGenres(genres);

        filmRepository.save(film);
    }

    private List<Genre> mapNamesToGenres(List<String> names) throws GenreNotFoundException {
        return names.stream().map(genreService::createIfNotExistsAndGet).toList();
    }

    /**
     * Searched for matching film names.
     */
    @Override
    public List<Film> searchFilmByTitle(String title) {
        return searchFilmByTitle(title, true);
    }

    @Override
    public List<Film> searchFilmByTitle(String title, boolean withoutDescriptions) {
        List<Film> films = filmRepository.findByTitleContainingIgnoreCase(title);
        if (withoutDescriptions) {
            films.forEach(film -> { film.setDesctiprion(null); });
        }
        return films;
    }

    @Override
    public List<FilmCatalogResponse> getCatalog(int pageNumber, int pageSize, String hint) {
        Pageable pageRequest = PageRequest.of(pageNumber, pageSize);
        hint = hint != null? hint.toLowerCase(): "";

        List<Film> films = filmRepository.getCatalog(hint, pageRequest);
        return films.stream().map(f -> new FilmCatalogResponse(
                f.getId(),
                f.getTitle(),
                f.getDirector(),
                f.getCompany(),
                f.getPosterFilename(),
                f.getGenres().stream().map(
                        Genre::getName
                ).toList()
        )).toList();
    }


}

