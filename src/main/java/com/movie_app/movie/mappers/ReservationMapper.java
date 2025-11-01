package com.movie_app.movie.mappers;

import com.movie_app.movie.model.ReservationEntity;
import com.movie_app.movie.model.dto.ReservationDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
    ReservationEntity asEntity(ReservationDTO src);

    ReservationDTO asModel(ReservationEntity src);
}
