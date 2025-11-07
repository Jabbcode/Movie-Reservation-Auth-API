package com.movie_app.movie.mappers;

import com.movie_app.movie.model.Reservation;
import com.movie_app.movie.model.dto.filters.FilterReservationDTO;
import com.movie_app.movie.repositories.mysql.entity.ReservationEntity;
import com.movie_app.movie.model.dto.ReservationDTO;
import com.movie_app.movie.shared.filters.Filter;
import com.movie_app.movie.shared.filters.FilterReservation;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    ReservationDTO asDTO(Reservation src);

    Reservation asModel(ReservationEntity src);

    Reservation asModel(ReservationDTO src);

    ReservationEntity asEntity(Reservation src);

    @BeanMapping(resultType = Filter.class)
    FilterReservation asModel(FilterReservationDTO src);

}
