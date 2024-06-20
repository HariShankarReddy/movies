package com.wissen.movies.repository;

import com.wissen.movies.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByMovieShowingId(Long movieShowingId);
}
