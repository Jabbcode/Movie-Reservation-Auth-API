package com.movie_app.movie.shared.filters;

import lombok.*;

import java.io.Serial;
import java.util.Collection;

@AllArgsConstructor
@Builder(toBuilder = true)
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Filter implements FilterMovie, FilterReservation {

    @Serial
    private static final long serialVersionUID = 8832327472756517114L;

    private String title;

    private String username;
}
