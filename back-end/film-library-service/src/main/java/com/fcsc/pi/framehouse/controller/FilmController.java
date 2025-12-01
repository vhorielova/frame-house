package com.fcsc.pi.framehouse.controller;

import com.fcsc.pi.framehouse.dto.CreateFilmRequest;
import com.fcsc.pi.framehouse.service.FilmService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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



}
