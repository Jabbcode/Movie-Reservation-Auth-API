package com.movie_app.movie.repositories;

import com.movie_app.movie.model.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long>, MovieRepositoryCustom {
}
