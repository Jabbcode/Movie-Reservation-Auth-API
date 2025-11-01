package com.movie_app.movie.shared.utils;

import com.movie_app.movie.model.QMovieEntity;

public class MovieWhereBuilder extends WhereBuilder<MovieWhereBuilder> {

    private final QMovieEntity movie = QMovieEntity.movieEntity;

    private MovieWhereBuilder() {}

    public static MovieWhereBuilder create() {
        return new MovieWhereBuilder();
    }

    public MovieWhereBuilder title(String title) {
        if (title != null && !title.isBlank()) {
            builder.and(movie.title.containsIgnoreCase(title));
        }
        return self();
    }

    public MovieWhereBuilder description(String description) {
        if (description != null && !description.isBlank()) {
            builder.and(movie.description.containsIgnoreCase(description));
        }
        return self();
    }

    public MovieWhereBuilder durationBetween(Number min, Number max) {
        if (min != null && max != null) {
            builder.and(movie.duration.between(min.intValue(), max.intValue()));
        } else if (min != null) {
            builder.and(movie.duration.goe(min.intValue()));
        } else if (max != null) {
            builder.and(movie.duration.loe(max.intValue()));
        }
        return self();
    }

}