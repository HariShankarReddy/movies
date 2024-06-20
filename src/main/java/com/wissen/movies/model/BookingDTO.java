package com.wissen.movies.model;

import com.wissen.movies.entity.Seat;

import java.time.LocalDateTime;
import java.util.List;

public class BookingDTO {
    String userName;
    Long movieShowingId;

    List<Long> seatIds;

    LocalDateTime bookingTime;

    Long totalPrice;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getMovieShowingId() {
        return movieShowingId;
    }

    public void setMovieShowingId(Long movieShowingId) {
        this.movieShowingId = movieShowingId;
    }

    public List<Long> getSeatIds() {
        return seatIds;
    }

    public void setSeatIds(List<Long> seatIds) {
        this.seatIds = seatIds;
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
}
