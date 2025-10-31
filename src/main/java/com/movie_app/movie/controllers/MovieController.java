package com.movie_app.movie.controllers;

import com.movie_app.movie.model.dto.MovieDTO;
import com.movie_app.movie.services.IMovieService;
import com.movie_app.movie.services.Impl.MovieServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    private IMovieService movieService;

    @GetMapping
    public ResponseEntity<List<MovieDTO>> findAll() {
        List<MovieDTO> movies = this.movieService.findAll();
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> findById(@PathVariable String id) {
        MovieDTO movie = this.movieService.findById(id);
        return ResponseEntity.ok(movie);
    }

    @PostMapping
    public ResponseEntity<MovieDTO> save(@Valid @RequestBody MovieDTO movieDto) {
        MovieDTO newMovie = this.movieService.save(movieDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newMovie);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MovieDTO> update(@PathVariable String id, @RequestBody MovieDTO movieDto) {
        MovieDTO updatedMovie = movieService.update(id, movieDto);
        return ResponseEntity.ok(updatedMovie);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        movieService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
