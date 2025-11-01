package com.movie_app.movie.repositories;

import com.movie_app.movie.model.ReservationEntity;
import com.movie_app.movie.shared.filters.ReservationFilter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReservationRepositoryImpl implements ReservationRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ReservationEntity> findByFilter(ReservationFilter filter) {
        StringBuilder jpql = new StringBuilder("SELECT r FROM ReservationEntity r WHERE 1=1");

        if (filter.getMovieId() != null) {
            jpql.append(" AND r.movie.id = :movieId");
        }

        TypedQuery<ReservationEntity> query = entityManager.createQuery(jpql.toString(), ReservationEntity.class);

        if (filter.getMovieId() != null) {
            query.setParameter("movieId", filter.getMovieId());
        }

        return query.getResultList();
    }
}
