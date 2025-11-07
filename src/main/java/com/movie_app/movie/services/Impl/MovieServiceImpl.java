package com.movie_app.movie.services.Impl;

import com.movie_app.movie.exception.ResourceAlreadyExistsException;
import com.movie_app.movie.exception.ResourceNotFoundException;
import com.movie_app.movie.mappers.MovieMapper;
import com.movie_app.movie.model.Movie;
import com.movie_app.movie.repositories.mysql.MovieRepository;
import com.movie_app.movie.repositories.mysql.entity.MovieEntity;
import com.movie_app.movie.services.MovieService;
import com.movie_app.movie.shared.filters.Filter;
import com.movie_app.movie.shared.filters.FilterMovie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    private final MovieMapper movieMapper;

    @Override
    @Transactional(readOnly = true)
    public Collection<Movie> findByFilter(final FilterMovie filter) {
        return this.movieRepository.findByFilter(filter)
                .stream()
                .map(this.movieMapper::asModel)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Movie findById(final Integer id) {
        MovieEntity movieEntity = this.movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontro la pelicula con ese ID: " + id));

        return this.movieMapper.asModel(movieEntity);
    }

    @Override
    @Transactional
    public Movie save(final Movie movie) {
        if (this.movieRepository.existsByTitleIgnoreCase(movie.getTitle())) {
            throw new ResourceAlreadyExistsException("Ya existe una película con el título: " + movie.getTitle());
        }

        final MovieEntity newMovieEntity = this.movieRepository.save(this.movieMapper.asEntity(movie));
        return this.movieMapper.asModel(newMovieEntity);
    }

    @Override
    @Transactional
    public Movie update(final Integer id, final Movie movie) {
        final MovieEntity existing = this.movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Película no encontrada para actualizar"));

        if (movie.getTitle() != null && !movie.getTitle().isBlank()) {
            existing.setTitle(movie.getTitle());
        }

        if (movie.getDescription() != null && !movie.getDescription().isBlank()) {
            existing.setDescription(movie.getDescription());
        }

        if (movie.getDuration() != null && movie.getDuration().doubleValue() > 0) {
            existing.setDuration(movie.getDuration());
        }

        return movieMapper.asModel(this.movieRepository.save(existing));
    }


    @Override
    @Transactional
    public void delete(final Integer id) {
        if (!this.movieRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se puede eliminar, la película no existe");
        }
        this.movieRepository.deleteById(id);
    }
}
