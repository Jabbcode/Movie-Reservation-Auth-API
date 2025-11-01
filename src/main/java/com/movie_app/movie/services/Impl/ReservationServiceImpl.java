package com.movie_app.movie.services.Impl;

import com.movie_app.movie.exception.ResourceNotFoundException;
import com.movie_app.movie.mappers.MovieMapper;
import com.movie_app.movie.mappers.ReservationMapper;
import com.movie_app.movie.model.MovieEntity;
import com.movie_app.movie.model.ReservationEntity;
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
        List<ReservationEntity> results = reservationRepository.findByFilter(filter);

        return results.stream().map(reservation -> {
            ReservationDTO reservationDTO = reservationMapper.asModel(reservation);

            movieRepository.findById(reservation.getMovie().getId()).ifPresent(movie -> reservationDTO.setMovie(movieMapper.asModel(movie)));

            return reservationDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public ReservationDTO findById(Long id) {
        ReservationEntity reservation = reservationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No se encontró la reserva con ID: " + id));

        ReservationDTO reservationDTO = reservationMapper.asModel(reservation);
        movieRepository.findById(reservation.getMovie().getId()).ifPresent(movie -> reservationDTO.setMovie(movieMapper.asModel(movie)));

        return reservationDTO;
    }

    @Override
    public ReservationDTO save(ReservationDTO reservationDTO) {
        MovieEntity movieEntity = movieRepository.findById(reservationDTO.getMovieId()).orElseThrow(() -> new ResourceNotFoundException("No se encontró la película con ID: " + reservationDTO.getMovieId()));

        ReservationEntity reservation = reservationMapper.asEntity(reservationDTO);
        ReservationEntity saved = reservationRepository.save(reservation);

        ReservationDTO result = reservationMapper.asModel(saved);
        result.setMovie(movieMapper.asModel(movieEntity));

        return result;
    }

    @Override
    public void delete(Long id) {
        ReservationEntity reservation = reservationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No se encontró la reserva con ID: " + id));
        reservationRepository.delete(reservation);
    }
}
