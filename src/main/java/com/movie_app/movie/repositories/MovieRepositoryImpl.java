package com.movie_app.movie.repositories;

import com.movie_app.movie.model.MovieEntity;
import com.movie_app.movie.model.QMovieEntity;
import com.movie_app.movie.shared.filters.MovieFilter;
import com.movie_app.movie.shared.utils.MovieWhereBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovieRepositoryImpl implements MovieRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<MovieEntity> findByFilter(MovieFilter filter) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QMovieEntity movie = QMovieEntity.movieEntity;

        var where = MovieWhereBuilder.create()
                .title(filter.getTitle())
                .description(filter.getDescription())
                .durationBetween(filter.getMinDuration(), filter.getMaxDuration())
                .build();

        return queryFactory
                .selectFrom(movie)
                .where(where)
                .fetch();
    }
}
