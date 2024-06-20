package com.wissen.movies.services;

import com.wissen.movies.entity.MovieShowing;
import com.wissen.movies.repository.MovieShowingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieShowingService {

    @Autowired
    private MovieShowingRepository movieShowingRepository;

    public List<MovieShowing> getAllMovieShowings() {
        return movieShowingRepository.findAll();
    }

    public Optional<MovieShowing> getMovieShowingById(Long id) {
        return movieShowingRepository.findById(id);
    }

    public List<MovieShowing> getMovieShowingsByMovieId(Long movieId) {
        return movieShowingRepository.findByMovieId(movieId);
    }

    public MovieShowing saveMovieShowing(MovieShowing movieShowing) {
        return movieShowingRepository.save(movieShowing);
    }

    public void deleteMovieShowing(Long id) {
        movieShowingRepository.deleteById(id);
    }

    public MovieShowing findMovieShowingWithSeats(Long id){
        return movieShowingRepository.findMovieShowingWithSeats(id);
    }
}
