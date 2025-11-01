package com.movie_app.movie.controllers;

import com.movie_app.movie.model.dto.ReservationDTO;
import com.movie_app.movie.services.IReservationService;
import com.movie_app.movie.shared.filters.ReservationFilter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private IReservationService reservationService;

    @PostMapping("/search")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<ReservationDTO>> findByFilter(@RequestBody ReservationFilter filter) {
        return ResponseEntity.ok(this.reservationService.findByFilter(filter));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ReservationDTO> findById(@PathVariable Long id) {
        ReservationDTO reservation = reservationService.findById(id);
        return ResponseEntity.ok(reservation);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<ReservationDTO> save(@Valid @RequestBody ReservationDTO reservationDto) {
        return ResponseEntity.ok(this.reservationService.save(reservationDto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.reservationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
