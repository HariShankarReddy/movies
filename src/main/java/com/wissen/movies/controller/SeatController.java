package com.wissen.movies.controller;

import com.wissen.movies.entity.Seat;
import com.wissen.movies.services.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seats")
@CrossOrigin
public class SeatController {

    @Autowired
    private SeatService seatService;

    @GetMapping("/{id}")
    public ResponseEntity<List<Seat>> getAllSeatsForMovieShowing(@PathVariable Long id) {

        List<Seat> seats = seatService.getSeatsByMovieShowingId(id);
        return new ResponseEntity<>(seats, HttpStatus.OK);
    }

    @PostMapping("/updateSeats")
    public ResponseEntity<List<Seat>>  updateSeat( @RequestBody List<Seat> seats) {
        seats.forEach(s->{
            seatService.updateSeatAvailability( s);
        });
        return new ResponseEntity<>(seats, HttpStatus.OK);
    }
}
