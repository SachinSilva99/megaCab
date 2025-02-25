package org.example.demo1.controller;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.example.demo1.annotations.RequestBody;
import org.example.demo1.annotations.RequestMapping;
import org.example.demo1.dto.request.BookingRequestDTO;
import org.example.demo1.dto.response.BookingResponseDTO;
import org.example.demo1.service.BookingService;
import org.example.demo1.util.ResponseDTO;

/**
 * Author : SachinSilva
 */
@RequestMapping("/bookings")
@ApplicationScoped
public class BookController {
    @Inject
    private  BookingService bookingService;

    @RequestMapping(value = "", method = RequestMapping.HttpMethod.POST)
    public ResponseDTO<BookingResponseDTO> book(@RequestBody BookingRequestDTO requestDTO) {
        return bookingService.createBooking(requestDTO);
    }

}
