package com.fcsc.pi.framehouse.repository;

import com.fcsc.pi.framehouse.models.Film;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film,Integer> {
    List<Film> findByTitleContainingIgnoreCase(String title);

    @Query("SELECT f FROM Film f WHERE " +
            "LOWER(f.title) LIKE CONCAT('%', :hint, '%') OR " +
            "LOWER(f.company) LIKE CONCAT(:hint, '%') OR " +
            "LOWER(f.director) LIKE CONCAT(:hint, '%')"
    )
    List<Film> getCatalog(@Param("hint") String hint, Pageable pageable);
}
