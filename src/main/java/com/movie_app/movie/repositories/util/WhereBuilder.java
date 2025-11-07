package com.movie_app.movie.repositories.util;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public class WhereBuilder {

    private final BooleanBuilder booleanbuilder = new BooleanBuilder();

    public Predicate build() {
        return this.booleanbuilder.getValue();
    }

    protected void addAndExpression(final Predicate expression) {
        this.booleanbuilder.and(expression);
    }

}