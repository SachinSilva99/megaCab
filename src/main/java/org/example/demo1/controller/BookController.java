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

    @RequestMapping(method = RequestMapping.HttpMethod.POST)
    public ResponseDTO<BookingResponseDTO> book(@RequestBody BookingRequestDTO requestDTO) {
        return bookingService.createBooking(requestDTO);
    }
    @RequestMapping(value = "/test", method = RequestMapping.HttpMethod.GET)
    public ResponseDTO<Object> test() {
        BookingRequestDTO requestDTO = BookingRequestDTO
                .builder()
                .carId(3)
                .customerId(3)
                .distanceId(1)
                .totalAmount(2000.0)
                .netAmount(1800.0)
                .taxAmount(200.0)
                .build();
        ResponseDTO<BookingResponseDTO> booking = bookingService.createBooking(requestDTO);
        System.out.println(booking);
        return null;
    }

}
