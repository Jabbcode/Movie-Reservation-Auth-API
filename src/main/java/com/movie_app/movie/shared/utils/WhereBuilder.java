package com.movie_app.movie.shared.utils;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class WhereBuilder {

    private final List<Criteria> criteriaList = new ArrayList<>();

    private WhereBuilder() {
    }

    public static WhereBuilder create() {
        return new WhereBuilder();
    }

    public WhereBuilder like(String field, String value) {
        if (value != null && !value.isBlank()) {
            criteriaList.add(Criteria.where(field).regex(value, "i"));
        }
        return this;
    }

    public WhereBuilder eq(String field, Object value) {
        if (value != null) {
            criteriaList.add(Criteria.where(field).is(value));
        }
        return this;
    }

    public WhereBuilder betweenDates(String field, LocalDateTime start, LocalDateTime end) {
        if (start != null && end != null) {
            criteriaList.add(Criteria.where(field).gte(start).lte(end));
        } else if (start != null) {
            criteriaList.add(Criteria.where(field).gte(start));
        } else if (end != null) {
            criteriaList.add(Criteria.where(field).lte(end));
        }
        return this;
    }

    public WhereBuilder betweenNumbers(String field, Number min, Number max) {
        if (min != null && max != null) {
            criteriaList.add(Criteria.where(field).gte(min).lte(max));
        } else if (min != null) {
            criteriaList.add(Criteria.where(field).gte(min));
        } else if (max != null) {
            criteriaList.add(Criteria.where(field).lte(max));
        }
        return this;
    }

    public Query build() {
        Criteria criteria = new Criteria();
        if (!criteriaList.isEmpty()) {
            criteria.andOperator(criteriaList.toArray(new Criteria[0]));
        }
        return new Query(criteria);
    }
}
