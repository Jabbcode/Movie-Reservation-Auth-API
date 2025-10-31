package com.movie_app.movie.repositories;

import com.movie_app.movie.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String>, MovieRepositoryCustom {
}
