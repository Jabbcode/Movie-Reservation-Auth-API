package com.movie_app.movie.repositories;

import com.movie_app.movie.model.MovieEntity;
import com.movie_app.movie.shared.filters.MovieFilter;

import java.util.List;

public interface MovieRepositoryCustom {
    List<MovieEntity> findByFilter(MovieFilter filter);
}
