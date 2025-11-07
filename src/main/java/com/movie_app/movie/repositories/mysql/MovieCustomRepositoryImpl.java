package com.movie_app.movie.repositories.mysql;

import com.movie_app.movie.repositories.mysql.entity.MovieEntity;
import com.movie_app.movie.repositories.mysql.entity.QMovieEntity;
import com.movie_app.movie.shared.filters.FilterMovie;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.Collection;

@Repository
public class MovieCustomRepositoryImpl implements MovieCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Collection<MovieEntity> findByFilter(final FilterMovie filter) {
        final JPAQueryFactory queryFactory = new JPAQueryFactory(this.entityManager);
        final QMovieEntity m = new QMovieEntity("m");

        return queryFactory
                .selectFrom(m)
                .where(this.generateWhere(filter, m))
                .fetch();
    }

    private BooleanExpression generateWhere(final FilterMovie filter, final QMovieEntity m) {
        return new WhereBuilder(m)
                .titles(filter.getTitle())
                .build();
    }

    @RequiredArgsConstructor
    private class WhereBuilder {
        private final QMovieEntity m;

        private BooleanExpression booleanExpression = Expressions.asBoolean(true).isTrue();

        WhereBuilder titles(final Collection<String> titles) {
            if (!CollectionUtils.isEmpty(titles)) {
                this.booleanExpression = this.booleanExpression
                        .and(this.m.title.in(titles));
            }
            return this;
        }

        BooleanExpression build() {
            return this.booleanExpression;
        }
    }
}
