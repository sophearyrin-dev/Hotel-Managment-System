package com.sa.sample.project.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sa.sample.project.dto.ResponseEntityDTO;
import com.sa.sample.project.jwt.JwtUtils;
import com.sa.sample.project.model.Booking;
import com.sa.sample.project.service.BookingHotelService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@AllArgsConstructor
@RequestMapping("/")
@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class BookingController {
     private final BookingHotelService bookingService;

     @Autowired
             JwtUtils jwtUtils;

    @PostMapping("/")
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity<?> saveBooking(@RequestBody Booking booking, HttpServletRequest request ) throws JsonProcessingException {
         return bookingService.save(booking,request);
    }

    @PostMapping("/front")
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity<?> saveBookingFrontEnd(@RequestBody Booking booking ) throws JsonProcessingException {
        return bookingService.saveBackend(booking);
    }

@CrossOrigin("http://localhost:3000")
    @GetMapping("/")
    private List<Booking> bookings() {
        return bookingService.findAll();
    }

    @GetMapping("/{bookingId}")
    private ResponseEntityDTO findBookingById(@PathVariable ("bookingId") String bookingId) {
        return bookingService.findById(bookingId);
    }

    @PutMapping("/{bookingId}")
    public ResponseEntity<?> updateBooking(@PathVariable ("bookingId") String bookingId, @RequestBody Booking booking, HttpServletRequest request) throws JsonProcessingException {
        bookingService.findById(bookingId);
        booking.setBookingId(bookingId);
        return bookingService.save(booking,request);
    }

    @DeleteMapping("/{bookingId}")
    private void deleteBookingById(@PathVariable ("bookingId") String bookingId) {
        bookingService.deleteById(bookingId);
    }
}
