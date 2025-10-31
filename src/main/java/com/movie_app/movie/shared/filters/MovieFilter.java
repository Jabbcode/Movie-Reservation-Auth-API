package com.movie_app.movie.shared.filters;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MovieFilter extends Filter{

    private String title;

    private String description;

    private Number minDuration;

    private Number maxDuration;
}
