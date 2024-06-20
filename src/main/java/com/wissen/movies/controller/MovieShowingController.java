package com.wissen.movies.controller;

import com.wissen.movies.entity.Movie;
import com.wissen.movies.entity.MovieShowing;
import com.wissen.movies.entity.Seat;
import com.wissen.movies.model.MovieShowingDTO;
import com.wissen.movies.services.MovieService;
import com.wissen.movies.services.MovieShowingService;
import com.wissen.movies.services.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movie-showings")
@CrossOrigin
public class MovieShowingController {

    @Autowired
    private MovieShowingService movieShowingService;

    @Autowired
    private SeatService seatService;

    @Autowired
    private MovieService movieService;

    @GetMapping
    public List<MovieShowing> getAllMovieShowings() {
        return movieShowingService.getAllMovieShowings();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieShowing> getMovieShowingById(@PathVariable Long id) {
        MovieShowing movieShowing = movieShowingService.findMovieShowingWithSeats(id);

        return ResponseEntity.ok(movieShowing);

    }

    @GetMapping("/movie/{movieId}")
    public List<MovieShowing> getMovieShowingsByMovieId(@PathVariable Long movieId) {
        return movieShowingService.getMovieShowingsByMovieId(movieId);
    }

    @PostMapping(value = "/create",consumes = "application/json", produces = "application/json")
    public ResponseEntity<MovieShowing> createMovieShowing(@RequestBody MovieShowingDTO movieShowingDto) {
        Movie m = movieService.getMovieById(movieShowingDto.getMovieId()).orElse(null);
        if(m!=null) {
            MovieShowing movieShowing = new MovieShowing();
            movieShowing.setMovie(m);
            movieShowing.setShowTime(movieShowingDto.getShowTime());
            MovieShowing ms = movieShowingService.saveMovieShowing(movieShowing);
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 10; j++) {
                    Seat s = new Seat();
                    s.setMovieShowing(ms);
                    s.setAvailable(true);
                    s.setColumn(j);
                    s.setRow(i);
                    s.setSeatNumber(i + "_" + j);
                    seatService.saveSeat(s);
                }

            }

            return ResponseEntity.ok(ms);
        }
        return ResponseEntity.ok(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieShowing> updateMovieShowing(@PathVariable Long id, @RequestBody MovieShowing movieShowingDetails) {
        Optional<MovieShowing> movieShowing = movieShowingService.getMovieShowingById(id);
        if (movieShowing.isPresent()) {
            MovieShowing existingMovieShowing = movieShowing.get();
            existingMovieShowing.setMovie(movieShowingDetails.getMovie());
            existingMovieShowing.setShowTime(movieShowingDetails.getShowTime());
            return ResponseEntity.ok(movieShowingService.saveMovieShowing(existingMovieShowing));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovieShowing(@PathVariable Long id) {
        Optional<MovieShowing> movieShowing = movieShowingService.getMovieShowingById(id);
        if (movieShowing.isPresent()) {
            movieShowingService.deleteMovieShowing(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

