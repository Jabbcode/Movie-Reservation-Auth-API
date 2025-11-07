package com.movie_app.movie.controllers;

import com.movie_app.movie.mappers.MovieMapper;
import com.movie_app.movie.model.dto.MovieDTO;
import com.movie_app.movie.model.dto.filters.FilterMovieDTO;
import com.movie_app.movie.services.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    private final MovieMapper movieMapper;

    @PostMapping("/search")
    @PreAuthorize("permitAll()")
    public ResponseEntity<Collection<MovieDTO>> findByFilter(final FilterMovieDTO filterDTO) {
        final Collection<MovieDTO> movies = this.movieService.findByFilter(this.movieMapper.asModel(filterDTO))
                .stream()
                .map(this.movieMapper::asDTO)
                .toList();
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MovieDTO> findById(final @PathVariable Integer id) {
        final MovieDTO movie = this.movieMapper.asDTO(this.movieService.findById(id));
        return ResponseEntity.ok(movie);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MovieDTO> save(final @Valid @RequestBody MovieDTO movieDto) {
        final MovieDTO newMovie = this.movieMapper.asDTO(this.movieService.save(this.movieMapper.asModel(movieDto)));
        return ResponseEntity.status(HttpStatus.CREATED).body(newMovie);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MovieDTO> update(final @PathVariable Integer id, final @RequestBody MovieDTO movieDto) {
        final MovieDTO updatedMovie = this.movieMapper.asDTO(this.movieService.update(id, this.movieMapper.asModel(movieDto)));
        return ResponseEntity.ok(updatedMovie);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(final @PathVariable Integer id) {
        this.movieService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
