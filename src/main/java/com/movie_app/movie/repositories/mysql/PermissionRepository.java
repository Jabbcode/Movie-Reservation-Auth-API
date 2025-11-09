package com.movie_app.movie.repositories.mysql;

import com.movie_app.movie.model.auth.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<PermissionEntity, Integer> {
}
