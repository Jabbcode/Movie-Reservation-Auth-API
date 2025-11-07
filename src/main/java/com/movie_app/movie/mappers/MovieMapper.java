package com.movie_app.movie.mappers;

import com.movie_app.movie.model.Movie;
import com.movie_app.movie.model.dto.filters.FilterMovieDTO;
import com.movie_app.movie.repositories.mysql.entity.MovieEntity;
import com.movie_app.movie.model.dto.MovieDTO;
import com.movie_app.movie.shared.filters.Filter;
import com.movie_app.movie.shared.filters.FilterMovie;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    MovieDTO asDTO(Movie src);

    @Mapping(target = "reservations", ignore = true)
    Movie asModel(MovieEntity src);

    Movie asModel(MovieDTO src);

    @Mapping(target = "reservations", ignore = true)
    MovieEntity asEntity(Movie src);

    @BeanMapping(resultType = Filter.class)
    FilterMovie asModel(FilterMovieDTO src);

}
