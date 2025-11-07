package com.movie_app.movie.services.Impl;

import com.movie_app.movie.exception.ResourceNotFoundException;
import com.movie_app.movie.mappers.MovieMapper;
import com.movie_app.movie.mappers.ReservationMapper;
import com.movie_app.movie.model.Reservation;
import com.movie_app.movie.repositories.mysql.MovieRepository;
import com.movie_app.movie.repositories.mysql.ReservationRepository;
import com.movie_app.movie.repositories.mysql.entity.MovieEntity;
import com.movie_app.movie.repositories.mysql.entity.ReservationEntity;
import com.movie_app.movie.services.ReservationService;
import com.movie_app.movie.shared.filters.FilterReservation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    private final ReservationMapper reservationMapper;

    private final MovieRepository movieRepository;

    private final MovieMapper movieMapper;

    @Override
    @Transactional(readOnly = true)
    public Collection<Reservation> findByFilter(FilterReservation filter) {
        return this.reservationRepository.findByFilter(filter)
                .stream()
                .map(this.reservationMapper::asModel)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Reservation findById(Integer id) {
        ReservationEntity reservation = this.reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró la reserva con ID: " + id));

        Reservation reservationFound = reservationMapper.asModel(reservation);
        this.movieRepository.findById(reservation.getMovie().getId())
                .ifPresent(movie -> reservationFound.setMovie(this.movieMapper.asModel(movie)));

        return reservationFound;
    }

    @Override
    @Transactional
    public Reservation save(final Reservation reservation) {
        final MovieEntity movieEntity = this.movieRepository.findById(reservation.getMovie().getId())
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró la película con ID: " + reservation.getMovie().getId()));

        final ReservationEntity newReservation = this.reservationRepository.save(this.reservationMapper.asEntity(reservation));

        Reservation result = this.reservationMapper.asModel(newReservation);
        result.setMovie(this.movieMapper.asModel(movieEntity));

        return result;
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        ReservationEntity reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró la reserva con ID: " + id));
        reservationRepository.delete(reservation);
    }
}
