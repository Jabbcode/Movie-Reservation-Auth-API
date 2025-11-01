package com.movie_app.movie.services;

import com.movie_app.movie.model.dto.MovieDTO;
import com.movie_app.movie.shared.filters.MovieFilter;

import java.util.List;

public interface IMovieService {

    List<MovieDTO> findByFilter(MovieFilter filter);

    MovieDTO findById(Long id);

    MovieDTO save(MovieDTO movie);

    MovieDTO update(Long id, MovieDTO movie);

    void delete(Long id);
}
