package com.movie_app.movie.controllers;

import com.movie_app.movie.model.dto.MovieDTO;
import com.movie_app.movie.services.IMovieService;
import com.movie_app.movie.shared.filters.MovieFilter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    private IMovieService movieService;

    @PostMapping("/search")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<MovieDTO>> findByFilter(MovieFilter filter) {
        List<MovieDTO> movies = this.movieService.findByFilter(filter);
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MovieDTO> findById(@PathVariable Long id) {
        MovieDTO movie = this.movieService.findById(id);
        return ResponseEntity.ok(movie);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MovieDTO> save(@Valid @RequestBody MovieDTO movieDto) {
        MovieDTO newMovie = this.movieService.save(movieDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newMovie);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MovieDTO> update(@PathVariable Long id, @RequestBody MovieDTO movieDto) {
        MovieDTO updatedMovie = movieService.update(id, movieDto);
        return ResponseEntity.ok(updatedMovie);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        movieService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
