package com.movie_app.movie.shared.utils;


import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.time.LocalDateTime;

public class WhereBuilder {

    private final Query query;

    private WhereBuilder() {
        this.query = new Query();
    }

    public static WhereBuilder create() {
        return new WhereBuilder();
    }

    public WhereBuilder betweenDates(String field, LocalDateTime start, LocalDateTime end) {
        if (start != null && end != null) {
            query.addCriteria(Criteria.where(field).gte(start).lte(end));
        } else if (start != null) {
            query.addCriteria(Criteria.where(field).gte(start));
        } else if (end != null) {
            query.addCriteria(Criteria.where(field).lte(end));
        }
        return this;
    }

    public Query build() {
        return query;
    }
}
