package com.movie_app.movie.repositories.mysql;

import com.movie_app.movie.repositories.mysql.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Integer>, MovieCustomRepository {
}
