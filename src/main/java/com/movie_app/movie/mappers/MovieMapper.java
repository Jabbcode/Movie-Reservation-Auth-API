package com.movie_app.movie.mappers;

import com.movie_app.movie.model.MovieEntity;
import com.movie_app.movie.model.dto.MovieDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    MovieDTO asModel(MovieEntity src);

    MovieEntity asEntity(MovieDTO src);
}
