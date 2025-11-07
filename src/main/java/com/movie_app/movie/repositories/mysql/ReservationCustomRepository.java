package com.movie_app.movie.repositories.mysql;

import com.movie_app.movie.repositories.mysql.entity.ReservationEntity;
import com.movie_app.movie.shared.filters.FilterReservation;

import java.util.Collection;

public interface ReservationCustomRepository {
    Collection<ReservationEntity> findByFilter(FilterReservation filter);
}
