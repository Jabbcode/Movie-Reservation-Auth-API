package com.movie_app.movie.model.auth;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ROLES")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ROLE")
    private Integer id;

    @Column(name = "ROLE_NAME", unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleEnum roleEnum;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ROLE_PERMISSIONS",
            joinColumns = @JoinColumn(name = "ROLE_ID"),
            inverseJoinColumns = @JoinColumn(name = "PERMISSION_ID"))
    private Set<PermissionEntity> permissions = new HashSet<>();
}