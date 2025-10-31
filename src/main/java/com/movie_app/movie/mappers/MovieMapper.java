package com.movie_app.movie.mappers;

import com.movie_app.movie.model.Movie;
import com.movie_app.movie.model.dto.MovieDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    MovieDTO asModel(Movie src);

    Movie asEntity(MovieDTO src);
}
