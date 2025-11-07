package com.movie_app.movie.repositories.mysql;

import com.movie_app.movie.repositories.mysql.entity.QReservationEntity;
import com.movie_app.movie.repositories.mysql.entity.ReservationEntity;
import com.movie_app.movie.shared.filters.FilterReservation;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class ReservationCustomRepositoryImpl implements ReservationCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Collection<ReservationEntity> findByFilter(final FilterReservation filter) {
        final JPAQueryFactory queryFactory = new JPAQueryFactory(this.entityManager);
        final QReservationEntity r = new QReservationEntity("r");

        return queryFactory
                .selectFrom(r)
                .where(this.generateWhere(filter, r))
                .fetch();
    }

    private BooleanExpression generateWhere(final FilterReservation filter, final QReservationEntity r) {
        return new WhereBuilder(r)
                .username(filter.getUsername())
                .build();
    }

    @RequiredArgsConstructor
    private class WhereBuilder {
        private final QReservationEntity r;

        private BooleanExpression booleanExpression = Expressions.asBoolean(true).isTrue();

        WhereBuilder username(final String username) {
            if (username != null) {
                this.booleanExpression = this.booleanExpression
                        .and(this.r.username.in(username));
            }
            return this;
        }

        BooleanExpression build() {
            return this.booleanExpression;
        }

    }
}
