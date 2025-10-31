package com.movie_app.movie.repositories;

import com.movie_app.movie.model.Reservation;
import com.movie_app.movie.shared.filters.ReservationFilter;
import com.movie_app.movie.shared.utils.WhereBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReservationRepositoryImpl implements ReservationRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Reservation> findByFilter(ReservationFilter filter) {
        Query query = WhereBuilder.create()
                .betweenDates("reservationDate", filter.getStartDate(), filter.getEndDate())
                .build();

        return mongoTemplate.find(query, Reservation.class);
    }
}
