package com.wissen.movies.entity;

import jakarta.persistence.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "movie_showing_id", nullable = false)
    private MovieShowing movieShowing;

    @ManyToMany
    @JoinTable(
            name = "booking_seats",
            joinColumns = @JoinColumn(name = "booking_id"),
            inverseJoinColumns = @JoinColumn(name = "seat_id")
    )
    private List<Seat> bookedSeats;

    private LocalDateTime bookingTime;

    private Long totalPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MovieShowing getMovieShowing() {
        return movieShowing;
    }

    public void setMovieShowing(MovieShowing movieShowing) {
        this.movieShowing = movieShowing;
    }

    public List<Seat> getBookedSeats() {
        return bookedSeats;
    }

    public void setBookedSeats(List<Seat> bookedSeats) {
        this.bookedSeats = bookedSeats;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
