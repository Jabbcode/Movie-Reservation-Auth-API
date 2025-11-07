package com.movie_app.movie.services;

import com.movie_app.movie.model.Movie;
import com.movie_app.movie.shared.filters.FilterMovie;

import java.util.Collection;

public interface MovieService {

    Collection<Movie> findByFilter(FilterMovie filter);

    Movie findById(Integer id);

    Movie save(Movie movie);

    Movie update(Integer id, Movie movie);

    void delete(Integer id);
}
