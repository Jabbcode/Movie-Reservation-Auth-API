package com.movie_app.movie.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationDTO {

    private Long id;

    @NotBlank(message = "El nombre del cliente es obligatorio")
    private String username;

    @NotNull(message = "La fecha de reserca es obligatoria")
    @Future(message = "La fecha de la reservación debe ser en el futuro")
    private LocalDateTime reservationDate;

    @NotNull(message = "El ID de la película es obligatorio")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer movieId;

    private MovieDTO movie;
}
