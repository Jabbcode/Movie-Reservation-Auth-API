package com.movie_app.movie.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@Document(collection = "reservations")
public class Reservation {

    @Id
    private String id;

    private String userName;

    private LocalDateTime reservationDate;

    private String movieId;
}
