package com.movie_app.movie.services.Impl;

import com.movie_app.movie.exception.ResourceAlreadyExistsException;
import com.movie_app.movie.exception.ResourceNotFoundException;
import com.movie_app.movie.mappers.MovieMapper;
import com.movie_app.movie.model.MovieEntity;
import com.movie_app.movie.model.dto.MovieDTO;
import com.movie_app.movie.repositories.MovieRepository;
import com.movie_app.movie.services.IMovieService;
import com.movie_app.movie.shared.filters.MovieFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements IMovieService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    MovieMapper movieMapper;

    @Override
    public List<MovieDTO> findByFilter(MovieFilter filter) {
        return this.movieRepository.findByFilter(filter)
                .stream()
                .map(this.movieMapper::asModel)
                .collect(Collectors.toList());
    }

    @Override
    public MovieDTO findById(Long id) {
        MovieEntity movieEntity = this.movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontro la pelicula con ese ID: " + id));

        return this.movieMapper.asModel(movieEntity);

    }

    @Override
    public MovieDTO save(MovieDTO movieDto) {
        boolean exists = this.movieRepository.findAll().stream().anyMatch(m -> m.getTitle().equalsIgnoreCase(movieDto.getTitle()));

        if (exists) {
            throw new ResourceAlreadyExistsException("Ya existe una película con el título: " + movieDto.getTitle());
        }

        MovieEntity movieEntity = movieMapper.asEntity(movieDto);
        MovieEntity newMovieEntity = this.movieRepository.save(movieEntity);
        return movieMapper.asModel(newMovieEntity);
    }

    @Override
    public MovieDTO update(Long id, MovieDTO movieDto) {
        MovieEntity existing = this.movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Película no encontrada para actualizar"));

        if (movieDto.getTitle() != null && !movieDto.getTitle().isBlank()) {
            existing.setTitle(movieDto.getTitle());
        }

        if (movieDto.getDescription() != null && !movieDto.getDescription().isBlank()) {
            existing.setDescription(movieDto.getDescription());
        }

        if (movieDto.getDuration() != null && movieDto.getDuration().doubleValue() > 0) {
            existing.setDuration(movieDto.getDuration());
        }

        MovieEntity updated = this.movieRepository.save(existing);
        return movieMapper.asModel(updated);
    }


    @Override
    public void delete(Long id) {
        if (!this.movieRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se puede eliminar, la película no existe");
        }
        movieRepository.deleteById(id);
    }
}
