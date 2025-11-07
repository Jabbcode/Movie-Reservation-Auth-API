package com.movie_app.movie.repositories.mysql;

import com.movie_app.movie.repositories.mysql.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Integer>, ReservationCustomRepository {
}
