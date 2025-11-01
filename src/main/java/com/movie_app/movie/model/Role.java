package com.movie_app.movie.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "roles")
public class Role {

    @Id
    private String id;

    private RoleEmun roleEmun;
}
