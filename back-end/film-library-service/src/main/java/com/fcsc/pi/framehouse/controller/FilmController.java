package com.fcsc.pi.framehouse.controller;

import com.fcsc.pi.framehouse.dto.CreateFilmRequest;
import com.fcsc.pi.framehouse.models.Film;
import com.fcsc.pi.framehouse.service.FilmService;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/films")
public class FilmController {

    final FilmService filmService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> addFilm(
            @RequestPart("request") CreateFilmRequest request,
            @RequestPart("file") MultipartFile file
            ) {
        filmService.addFilm(request,file);
        return ResponseEntity.ok("Film was added successfully");
    }

    /**
     * Searches film with matching title.
     * Title must be at least 2 symbols.
     */
    @Validated
    @GetMapping("/search")
    public ResponseEntity<List<Film>> search(@RequestParam("title") @Size(min=2) String title) {
        return  ResponseEntity.ok(filmService.searchFilmByTitle(title));
    }
}
