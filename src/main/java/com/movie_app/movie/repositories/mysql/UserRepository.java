package com.movie_app.movie.repositories.mysql;

import com.movie_app.movie.model.auth.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findUserEntityByUsername(String username);
}
