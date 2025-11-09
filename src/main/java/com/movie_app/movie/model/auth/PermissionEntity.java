package com.movie_app.movie.model.auth;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "PERMISSIONS")
public class PermissionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PERMISSION")
    private Integer id;

    @Column(name = "NAME", unique = true, nullable = false, updatable = false)
    private String name;
}
