package com.movie_app.movie.services;

import com.movie_app.movie.model.Reservation;
import com.movie_app.movie.model.dto.ReservationDTO;
import com.movie_app.movie.shared.filters.FilterReservation;

import java.util.Collection;
import java.util.List;

public interface ReservationService {

    Collection<Reservation> findByFilter(FilterReservation filter);

    Reservation findById(Integer id);

    Reservation save(Reservation dto);

    void delete(Integer id);
}
