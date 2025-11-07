package com.movie_app.movie.controllers;

import com.movie_app.movie.mappers.ReservationMapper;
import com.movie_app.movie.model.dto.ReservationDTO;
import com.movie_app.movie.model.dto.filters.FilterReservationDTO;
import com.movie_app.movie.services.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    private final ReservationMapper reservationMapper;

    @PostMapping("/search")
    @PreAuthorize("permitAll()")
    public ResponseEntity<Collection<ReservationDTO>> findByFilter(final @RequestBody FilterReservationDTO filterDTO) {
        final Collection<ReservationDTO> reservations = this.reservationService.findByFilter(this.reservationMapper.asModel(filterDTO))
                .stream()
                .map(this.reservationMapper::asDTO)
                .toList();
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ReservationDTO> findById(final @PathVariable Integer id) {
        final ReservationDTO reservation = this.reservationMapper.asDTO(this.reservationService.findById(id));
        return ResponseEntity.ok(reservation);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<ReservationDTO> save(final @Valid @RequestBody ReservationDTO reservationDto) {
        final ReservationDTO newReservation = this.reservationMapper.asDTO(this.reservationService.save(this.reservationMapper.asModel(reservationDto)));
        return ResponseEntity.status(HttpStatus.CREATED).body(newReservation);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(final @PathVariable Integer id) {
        this.reservationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
