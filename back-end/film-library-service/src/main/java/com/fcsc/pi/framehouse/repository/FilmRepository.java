package com.fcsc.pi.framehouse.repository;

import com.fcsc.pi.framehouse.models.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends JpaRepository<Film,Integer> {
}
