package com.movie_app.movie.config;

import com.movie_app.movie.model.auth.PermissionEntity;
import com.movie_app.movie.model.auth.RoleEntity;
import com.movie_app.movie.model.auth.RoleEnum;
import com.movie_app.movie.model.auth.UserEntity;
import com.movie_app.movie.repositories.mysql.PermissionRepository;
import com.movie_app.movie.repositories.mysql.RoleRepository;
import com.movie_app.movie.repositories.mysql.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Set;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(
            PermissionRepository permissionRepository,
            RoleRepository roleRepository,
            UserRepository userRepository
    ) {
        return args -> {

            // --- Crear permisos si no existen ---
//            if (permissionRepository.count() == 0) {
            permissionRepository.saveAll(List.of(
                    PermissionEntity.builder().name("CREATE_MOVIE").build(),
                    PermissionEntity.builder().name("CREATE_RESERVATION").build(),
                    PermissionEntity.builder().name("READ_MOVIE").build(),
                    PermissionEntity.builder().name("READ_RESERVATION").build(),
                    PermissionEntity.builder().name("UPDATE_MOVIE").build(),
                    PermissionEntity.builder().name("UPDATE_RESERVATION").build(),
                    PermissionEntity.builder().name("DELETE_MOVIE").build(),
                    PermissionEntity.builder().name("DELETE_RESERVATION").build()
            ));
//            }

            var create_movie = permissionRepository.findAll().stream().filter(p -> p.getName().equals("CREATE_MOVIE")).findFirst().get();
            var create_reservation = permissionRepository.findAll().stream().filter(p -> p.getName().equals("CREATE_RESERVATION")).findFirst().get();
            var read_movie = permissionRepository.findAll().stream().filter(p -> p.getName().equals("READ_MOVIE")).findFirst().get();
            var read_reservation = permissionRepository.findAll().stream().filter(p -> p.getName().equals("READ_RESERVATION")).findFirst().get();
            var update_movie = permissionRepository.findAll().stream().filter(p -> p.getName().equals("UPDATE_MOVIE")).findFirst().get();
            var update_reservation = permissionRepository.findAll().stream().filter(p -> p.getName().equals("UPDATE_RESERVATION")).findFirst().get();
            var delete_movie = permissionRepository.findAll().stream().filter(p -> p.getName().equals("DELETE_MOVIE")).findFirst().get();
            var delete_reservation = permissionRepository.findAll().stream().filter(p -> p.getName().equals("DELETE_RESERVATION")).findFirst().get();

            // --- Crear roles si no existen ---
//            if (roleRepository.count() == 0) {
                RoleEntity admin = RoleEntity.builder()
                        .roleEnum(RoleEnum.ADMIN)
                        .permissions(Set.of(
                                create_movie,
                                create_reservation,
                                read_movie,
                                read_reservation,
                                update_movie,
                                update_reservation,
                                delete_movie,
                                delete_reservation
                                ))
                        .build();

                RoleEntity user = RoleEntity.builder()
                        .roleEnum(RoleEnum.USER)
                        .permissions(Set.of(
                                create_reservation,
                                read_movie,
                                read_reservation
                        ))
                        .build();

                RoleEntity invited = RoleEntity.builder()
                        .roleEnum(RoleEnum.INVITED)
                        .permissions(Set.of(
                                read_movie,
                                read_reservation
                        ))
                        .build();

                roleRepository.saveAll(List.of(admin, user, invited));
//            }

            var roleAdmin = roleRepository.findByRoleEnum(RoleEnum.ADMIN).get();
            var roleUser = roleRepository.findByRoleEnum(RoleEnum.USER).get();
            var roleInvited = roleRepository.findByRoleEnum(RoleEnum.INVITED).get();

            // --- Crear usuarios solo si no existen ---
//            if (userRepository.count() == 0) {
                UserEntity userA = UserEntity.builder()
                        .username("one")
                        .email("one@test.com")
                        .password(new BCryptPasswordEncoder().encode("1234"))
                        .isEnabled(true)
                        .accountNoExpired(true)
                        .accountNoLocked(true)
                        .credentialNoExpired(true)
                        .roles(Set.of(roleAdmin))
                        .build();

                UserEntity userB = UserEntity.builder()
                        .username("two")
                        .email("two@test.com")
                        .password(new BCryptPasswordEncoder().encode("1234"))
                        .isEnabled(true)
                        .accountNoExpired(true)
                        .accountNoLocked(true)
                        .credentialNoExpired(true)
                        .roles(Set.of(roleUser))
                        .build();

                UserEntity userC = UserEntity.builder()
                        .username("three")
                        .email("three@test.com")
                        .password(new BCryptPasswordEncoder().encode("1234"))
                        .isEnabled(true)
                        .accountNoExpired(true)
                        .accountNoLocked(true)
                        .credentialNoExpired(true)
                        .roles(Set.of(roleInvited))
                        .build();

                userRepository.saveAll(List.of(userA, userB, userC));
//            }
        };
    }
}
