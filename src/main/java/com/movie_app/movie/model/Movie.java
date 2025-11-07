package com.movie_app.movie.model;

import lombok.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Movie implements Serializable {

    private static final long serialVersionUID = 5670395564673466262L;

    private Integer id;

    private String title;

    private String description;

    private Number duration;

    @Builder.Default
    @ToString.Exclude
    private Collection<Reservation> reservations = new HashSet<>();
}
