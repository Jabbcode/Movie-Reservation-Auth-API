package com.movie_app.movie.services;

import com.movie_app.movie.model.dto.ReservationDTO;
import com.movie_app.movie.shared.filters.ReservationFilter;

import java.util.List;

public interface IReservationService {

    List<ReservationDTO> findByFilter(ReservationFilter filter);

    ReservationDTO findById(Long id);

    ReservationDTO save(ReservationDTO dto);

    void delete(Long id);
}
