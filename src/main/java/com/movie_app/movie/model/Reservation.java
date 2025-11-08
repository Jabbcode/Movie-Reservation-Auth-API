package com.movie_app.movie.model;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Reservation implements Serializable {

    private static final long serialVersionUID = 8915080993986907406L;

    private Integer id;

    private String username;

    private LocalDateTime reservationDate;

    private Integer movieId;

    @ToString.Exclude
    private Movie movie;
}
