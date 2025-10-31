package com.movie_app.movie.repositories;

import com.movie_app.movie.model.Movie;
import com.movie_app.movie.shared.filters.MovieFilter;

import java.util.List;

public interface MovieRepositoryCustom {

    List<Movie> findByFilter(MovieFilter filter);
}
