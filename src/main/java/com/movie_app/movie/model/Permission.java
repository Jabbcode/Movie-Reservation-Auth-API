package com.movie_app.movie.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "permissions")
public class Permission {

    @Id
    private String id;

    private String name;
}
