package com.movie_app.movie.mappers;

import com.movie_app.movie.model.Reservation;
import com.movie_app.movie.model.dto.ReservationDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
    Reservation asEntity(ReservationDTO src);

    ReservationDTO asModel(Reservation src);
}
