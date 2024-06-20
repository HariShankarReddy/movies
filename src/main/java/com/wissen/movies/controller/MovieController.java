package com.wissen.movies.controller;

import com.wissen.movies.entity.Movie;
import com.wissen.movies.model.MovieDTO;
import com.wissen.movies.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movies")
@CrossOrigin
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        Optional<Movie> movie = movieService.getMovieById(id);
        return movie.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Movie createMovie(@RequestBody MovieDTO movieDto) {
        Movie movie = new Movie();
        movie.setTitle(movieDto.getTitle());
        movie.setSynopsis(movieDto.getSynopsis());
        movie.setReleaseDate(movieDto.getReleaseDate());
        return movieService.saveMovie(movie);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie movieDetails) {
        Optional<Movie> movie = movieService.getMovieById(id);
        if (movie.isPresent()) {
            Movie existingMovie = movie.get();
            existingMovie.setTitle(movieDetails.getTitle());
            existingMovie.setSynopsis(movieDetails.getSynopsis());
            existingMovie.setReleaseDate(movieDetails.getReleaseDate());
            return ResponseEntity.ok(movieService.saveMovie(existingMovie));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        Optional<Movie> movie = movieService.getMovieById(id);
        if (movie.isPresent()) {
            movieService.deleteMovie(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

