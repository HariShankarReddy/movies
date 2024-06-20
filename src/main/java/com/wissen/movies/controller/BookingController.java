package com.wissen.movies.controller;

import com.wissen.movies.entity.Booking;
import com.wissen.movies.entity.MovieShowing;
import com.wissen.movies.entity.Seat;
import com.wissen.movies.entity.User;
import com.wissen.movies.model.BookingDTO;
import com.wissen.movies.services.BookingService;
import com.wissen.movies.services.MovieShowingService;
import com.wissen.movies.services.SeatService;
import com.wissen.movies.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bookings")
@CrossOrigin
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private SeatService seatService;

    @Autowired
    private MovieShowingService movieShowingService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long id) {
        Optional<Booking> booking = bookingService.getBookingById(id);
        if (booking.isPresent()) {
            return ResponseEntity.ok(booking.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user/{userId}")
    public List<Booking> getBookingsByUserId(@PathVariable Long userId) {
        return bookingService.getBookingsByUserId(userId);
    }

    @GetMapping("/showtime/{showtimeId}")
    public List<Booking> getBookingsByShowtimeId(@PathVariable Long showtimeId) {
        return bookingService.getBookingsByShowtimeId(showtimeId);
    }

    @PostMapping
    public Booking createBooking(@RequestBody BookingDTO bookingDto) {
        User user = userDetailsService.loadUserByUsername(bookingDto.getUserName());
        MovieShowing ms = movieShowingService.getMovieShowingById(bookingDto.getMovieShowingId()).orElse(null);

        List<Seat> seats = new ArrayList<>();
        bookingDto.getSeatIds().forEach(s-> {
                Seat seat =  seatService.getSeatById(s).orElse(null);
                if(seat!=null) {
                    seat.setAvailable(false);
                    seatService.updateSeatAvailability(seat);
                    seats.add(seat);
                }

            }
        );
        Booking bookingCreate = new Booking();
        bookingCreate.setBookedSeats(seats);
        bookingCreate.setUser(user);
        bookingCreate.setMovieShowing(ms);
        bookingCreate.setBookingTime(bookingDto.getBookingTime());
        bookingCreate.setTotalPrice(bookingDto.getTotalPrice());
        return bookingService.saveBooking(bookingCreate);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Booking> updateBooking(@PathVariable Long id, @RequestBody Booking bookingDetails) {
        Optional<Booking> booking = bookingService.getBookingById(id);
        if (booking.isPresent()) {
            Booking existingBooking = booking.get();
            existingBooking.setUser(bookingDetails.getUser());
            existingBooking.setMovieShowing(bookingDetails.getMovieShowing());
            existingBooking.setBookingTime(bookingDetails.getBookingTime());
            existingBooking.setBookedSeats(bookingDetails.getBookedSeats());
            existingBooking.setTotalPrice(bookingDetails.getTotalPrice());
            return ResponseEntity.ok(bookingService.saveBooking(existingBooking));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        Optional<Booking> booking = bookingService.getBookingById(id);
        if (booking.isPresent()) {
            bookingService.deleteBooking(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

