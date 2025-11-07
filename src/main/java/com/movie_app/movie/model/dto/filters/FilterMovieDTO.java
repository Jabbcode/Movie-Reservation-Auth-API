package com.movie_app.movie.model.dto.filters;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilterMovieDTO {

    private Collection<String> title = new ArrayList<>();
}
