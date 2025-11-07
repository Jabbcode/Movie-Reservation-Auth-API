package com.movie_app.movie.repositories.mysql;

import com.movie_app.movie.repositories.mysql.entity.MovieEntity;
import com.movie_app.movie.shared.filters.FilterMovie;

import java.util.Collection;
import java.util.List;

public interface MovieCustomRepository {
    Collection<MovieEntity> findByFilter(FilterMovie filter);
}
