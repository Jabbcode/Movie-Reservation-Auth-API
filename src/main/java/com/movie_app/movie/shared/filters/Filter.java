package com.movie_app.movie.shared.filters;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public abstract class Filter {

    private LocalDateTime startDate;

    private LocalDateTime endDate;
}
