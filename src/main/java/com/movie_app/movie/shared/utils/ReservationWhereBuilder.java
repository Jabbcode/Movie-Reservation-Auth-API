package com.movie_app.movie.shared.utils;

import com.movie_app.movie.model.QReservationEntity;

public class ReservationWhereBuilder extends WhereBuilder<ReservationWhereBuilder> {

    private final QReservationEntity reservation = QReservationEntity.reservationEntity;

    private ReservationWhereBuilder() {}

    public static ReservationWhereBuilder create() {
        return new ReservationWhereBuilder();
    }

    public ReservationWhereBuilder userName(String userName) {
        if (userName != null && !userName.isBlank()) {
            builder.and(reservation.username.containsIgnoreCase(userName));
        }
        return self();
    }
}
