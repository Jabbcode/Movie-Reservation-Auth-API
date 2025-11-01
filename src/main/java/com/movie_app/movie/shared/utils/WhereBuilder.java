package com.movie_app.movie.shared.utils;

import com.querydsl.core.BooleanBuilder;

public abstract class WhereBuilder<T extends WhereBuilder<T>> {

    protected final BooleanBuilder builder = new BooleanBuilder();

    @SuppressWarnings("unchecked")
    protected T self() {
        return (T) this;
    }

    public BooleanBuilder build() {
        return builder;
    }
}