package com.wissen.movies.services;

import com.wissen.movies.entity.Seat;
import com.wissen.movies.repository.SeatRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeatService {

    @Autowired
    private SeatRepository seatRepository;

    public List<Seat> getAllSeats() {
        return seatRepository.findAll();
    }

    public Optional<Seat> getSeatById(Long id) {
        return seatRepository.findById(id);
    }

    public Seat saveSeat(Seat seat) {
        return seatRepository.save(seat);
    }

    public void deleteSeat(Long id) {
        seatRepository.deleteById(id);
    }

    public List<Seat> getSeatsByMovieShowingId(Long movieShowingId){
        return seatRepository.findByMovieShowingId(movieShowingId);
    }

    @Transactional
    public Seat updateSeatAvailability(Seat seat) {
        return seatRepository.findById(seat.getId())
                .map(existingSeat -> {
                    existingSeat.setAvailable(seat.isAvailable());
                    return seatRepository.save(existingSeat);
                })
                .orElseThrow(() -> new RuntimeException("Seat not found with id " + seat.getId()));
    }
}

