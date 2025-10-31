package com.movie_app.movie.repositories;

import com.movie_app.movie.model.Movie;
import com.movie_app.movie.shared.filters.MovieFilter;
import com.movie_app.movie.shared.utils.WhereBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovieRepositoryImpl implements MovieRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Movie> findByFilter(MovieFilter filter) {
        Query query = WhereBuilder.create()
                .like("title", filter.getTitle())
                .like("description", filter.getDescription())
                .betweenNumbers("duration", filter.getMinDuration(), filter.getMaxDuration())
                .build();

        return mongoTemplate.find(query, Movie.class);
    }
}
