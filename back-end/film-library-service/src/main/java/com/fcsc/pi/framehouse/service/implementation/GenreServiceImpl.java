package com.fcsc.pi.framehouse.service.implementation;

import com.fcsc.pi.framehouse.models.Genre;
import com.fcsc.pi.framehouse.repository.GenreRepository;
import com.fcsc.pi.framehouse.service.GenreService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GenreServiceImpl implements GenreService {

    private GenreRepository genreRepository;

    @Override
    public Genre createIfNotExistsAndGet(String name) {
        Optional<Genre> optionalGenre = genreRepository.findByName(name);

        if (optionalGenre.isPresent()) {
            return optionalGenre.get();
        } else {
            Genre genre = new Genre();
            genre.setName(name);
            return genreRepository.save(genre);
        }
    }
}
