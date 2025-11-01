package com.movie_app.movie.repositories;

import com.movie_app.movie.model.ReservationEntity;
import com.movie_app.movie.shared.filters.ReservationFilter;

import java.util.List;

public interface ReservationRepositoryCustom {
    List<ReservationEntity> findByFilter(ReservationFilter filter);
}
