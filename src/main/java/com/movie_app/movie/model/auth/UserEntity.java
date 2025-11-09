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
@Table(name = "USERS")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USER")
    private Integer id;

    @Column(name = "USERNAME", unique = true, nullable = false)
    private String username;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "EMAIL", unique = true, nullable = false)
    private String email;

    @Column(name = "IS_ENABLED")
    private boolean isEnabled;

    @Column(name = "ACCOUNT_NO_EXPIRED")
    private boolean accountNoExpired;

    @Column(name = "ACCOUNT_NO_LOCKED")
    private boolean accountNoLocked;

    @Column(name = "CREDENTIAL_NO_EXPIRED")
    private boolean credentialNoExpired;

    @Builder.Default
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_ROLES",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private Set<RoleEntity> roles = new HashSet<>();
}