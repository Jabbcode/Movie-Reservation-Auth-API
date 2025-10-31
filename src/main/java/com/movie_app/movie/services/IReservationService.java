package com.movie_app.movie.services;

import com.movie_app.movie.model.dto.ReservationDTO;

import java.util.List;

public interface IReservationService {

    List<ReservationDTO> findAll();

    ReservationDTO findById(String id);

    ReservationDTO save(ReservationDTO dto);

    void delete(String id);
}
