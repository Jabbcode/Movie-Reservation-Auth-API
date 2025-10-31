package com.movie_app.movie.services.Impl;

import com.movie_app.movie.exception.ResourceNotFoundException;
import com.movie_app.movie.mappers.MovieMapper;
import com.movie_app.movie.mappers.ReservationMapper;
import com.movie_app.movie.model.Movie;
import com.movie_app.movie.model.Reservation;
import com.movie_app.movie.model.dto.ReservationDTO;
import com.movie_app.movie.repositories.MovieRepository;
import com.movie_app.movie.repositories.ReservationRepository;
import com.movie_app.movie.services.IReservationService;
import com.movie_app.movie.shared.filters.ReservationFilter;
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
    MovieRepository movieRepository;

    @Autowired
    MovieMapper movieMapper;

    @Override
    public List<ReservationDTO> findByFilter(ReservationFilter filter) {
        List<Reservation> results = reservationRepository.findByFilter(filter);

        return results.stream().map(reservation -> {
            ReservationDTO reservationDTO = reservationMapper.asModel(reservation);

            movieRepository.findById(reservation.getMovieId()).ifPresent(movie -> reservationDTO.setMovie(movieMapper.asModel(movie)));

            return reservationDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public ReservationDTO findById(String id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No se encontró la reserva con ID: " + id));

        ReservationDTO reservationDTO = reservationMapper.asModel(reservation);
        movieRepository.findById(reservation.getMovieId()).ifPresent(movie -> reservationDTO.setMovie(movieMapper.asModel(movie)));

        return reservationDTO;
    }

    @Override
    public ReservationDTO save(ReservationDTO reservationDTO) {
        Movie movie = movieRepository.findById(reservationDTO.getMovieId()).orElseThrow(() -> new ResourceNotFoundException("No se encontró la película con ID: " + reservationDTO.getMovieId()));

        Reservation reservation = reservationMapper.asEntity(reservationDTO);
        Reservation saved = reservationRepository.save(reservation);

        ReservationDTO result = reservationMapper.asModel(saved);
        result.setMovie(movieMapper.asModel(movie));

        return result;
    }

    @Override
    public void delete(String id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No se encontró la reserva con ID: " + id));
        reservationRepository.delete(reservation);
    }
}
