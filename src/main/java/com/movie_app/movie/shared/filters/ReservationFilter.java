package com.movie_app.movie.shared.filters;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ReservationFilter extends Filter {

    private String movieId;

    private String userName;
}
