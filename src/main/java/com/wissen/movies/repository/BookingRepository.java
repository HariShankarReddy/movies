package com.wissen.movies.repository;

import com.wissen.movies.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Query("SELECT b FROM Booking b WHERE b.user.id = ?1")
    List<Booking> findByUserId(Long userId);

    @Query("SELECT b FROM Booking b WHERE b.movieShowing.id = ?1")
    List<Booking> findByShowtimeId(Long showtimeId);
}
