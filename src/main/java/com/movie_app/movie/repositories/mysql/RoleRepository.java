package com.movie_app.movie.repositories.mysql;

import com.movie_app.movie.model.auth.RoleEntity;
import com.movie_app.movie.model.auth.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
    Optional<RoleEntity> findByRoleEnum(RoleEnum roleEnum);
}
