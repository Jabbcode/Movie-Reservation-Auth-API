package com.movie_app.movie.repositories;

import com.movie_app.movie.model.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends MongoRepository<Reservation, String>, ReservationRepositoryCustom {
}
