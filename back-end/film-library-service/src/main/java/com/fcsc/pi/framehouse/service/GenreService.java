package com.fcsc.pi.framehouse.service;

import com.fcsc.pi.framehouse.models.Genre;

public interface GenreService {
    Genre createIfNotExistsAndGet(String name);
}
