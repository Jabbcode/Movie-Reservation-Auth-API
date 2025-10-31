package com.movie_app.movie.services.Impl;

import com.movie_app.movie.exception.ResourceNotFoundException;
import com.movie_app.movie.mappers.ReservationMapper;
import com.movie_app.movie.model.Reservation;
import com.movie_app.movie.model.dto.MovieDTO;
import com.movie_app.movie.model.dto.ReservationDTO;
import com.movie_app.movie.repositories.ReservationRepository;
import com.movie_app.movie.services.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements IReservationService {

    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    ReservationMapper reservationMapper;
    @Autowired
    MovieServiceImpl movieService;

    @Override
    public List<ReservationDTO> findAll() {
        return reservationRepository.findAll()
                .stream()
                .map(reservation -> {
                    ReservationDTO reservationDTO = reservationMapper.asModel(reservation);
                    reservationDTO.setMovie(this.movieService.findById(reservation.getMovieId()));
                    return reservationDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public ReservationDTO findById(String id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva no encontrada"));

        ReservationDTO dto = reservationMapper.asModel(reservation);
        dto.setMovie(movieService.findById(reservation.getMovieId()));
        return dto;
    }

    @Override
    public ReservationDTO save(ReservationDTO reservationDTO) {
        MovieDTO movie = movieService.findById(reservationDTO.getMovieId());
        if (movie == null) {
            throw new ResourceNotFoundException("La película asociada no existe");
        }

        Reservation reservation = reservationMapper.asEntity(reservationDTO);
        Reservation saved = reservationRepository.save(reservation);

        ReservationDTO response = reservationMapper.asModel(saved);
        response.setMovie(movie);
        return response;
    }

    @Override
    public void delete(String id) {
        if (!reservationRepository.existsById(id)) {
            throw new ResourceNotFoundException("La reserva no existe");
        }
        reservationRepository.deleteById(id);
    }
}
