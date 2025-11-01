package com.movie_app.movie.repositories;

import com.movie_app.movie.model.MovieEntity;
import com.movie_app.movie.model.QMovieEntity;
import com.movie_app.movie.model.QReservationEntity;
import com.movie_app.movie.model.ReservationEntity;
import com.movie_app.movie.shared.filters.MovieFilter;
import com.movie_app.movie.shared.filters.ReservationFilter;
import com.movie_app.movie.shared.utils.MovieWhereBuilder;
import com.movie_app.movie.shared.utils.ReservationWhereBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReservationRepositoryImpl implements ReservationRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ReservationEntity> findByFilter(ReservationFilter filter) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QReservationEntity movie = QReservationEntity.reservationEntity;

        var where = ReservationWhereBuilder.create()
                .userName(filter.getUserName())
                .build();

        return queryFactory
                .selectFrom(movie)
                .where(where)
                .fetch();
    }
}
