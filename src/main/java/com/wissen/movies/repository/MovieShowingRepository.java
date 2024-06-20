package com.wissen.movies.repository;

import com.wissen.movies.entity.MovieShowing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieShowingRepository extends JpaRepository<MovieShowing, Long> {
    List<MovieShowing> findByMovieId(Long movieId);
    // You can define custom query methods here if needed

    @Query("SELECT ms FROM MovieShowing ms LEFT JOIN FETCH ms.seats  WHERE ms.id = :id")
    MovieShowing findMovieShowingWithSeats(Long id);

    @Query("SELECT ms FROM MovieShowing ms JOIN FETCH ms.seats")
    MovieShowing findAllMovieShowingWithSeats();
}

