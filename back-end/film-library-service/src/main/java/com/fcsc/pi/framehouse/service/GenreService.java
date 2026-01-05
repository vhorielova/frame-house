package com.fcsc.pi.framehouse.service;

import com.fcsc.pi.framehouse.exceptions.GenreNotFoundException;
import com.fcsc.pi.framehouse.models.Genre;

public interface GenreService {
    Genre createIfNotExistsAndGet(String name) throws GenreNotFoundException;
}
