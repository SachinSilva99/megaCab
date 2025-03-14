package com.sachin.controller;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import com.sachin.annotations.RequestBody;
import com.sachin.annotations.RequestMapping;
import com.sachin.dto.request.BookingRequestDTO;
import com.sachin.dto.response.BookingDetailsResponseDTO;
import com.sachin.dto.response.BookingResponseDTO;
import com.sachin.service.BookingService;
import com.sachin.util.ResponseDTO;

import java.util.List;

/**
 * Author : SachinSilva
 */
@RequestMapping("/bookings")
@ApplicationScoped
public class BookController {
    @Inject
    private BookingService bookingService;

    @RequestMapping(method = RequestMapping.HttpMethod.POST)
    public ResponseDTO<BookingResponseDTO> book(@RequestBody BookingRequestDTO requestDTO) {
        return bookingService.createBooking(requestDTO);
    }

    @RequestMapping(value = "/all-bookings", method = RequestMapping.HttpMethod.GET)
    public ResponseDTO<List<BookingDetailsResponseDTO>> allBookings() {
        return bookingService.allBookings();
    }
}
