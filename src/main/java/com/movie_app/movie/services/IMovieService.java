package com.movie_app.movie.services;

import com.movie_app.movie.model.dto.MovieDTO;

import java.util.List;

public interface IMovieService {

    List<MovieDTO> findAll();

    MovieDTO findById(String id);

    MovieDTO save(MovieDTO movie);

    MovieDTO update(String id, MovieDTO movie);

    void delete(String id);
}
