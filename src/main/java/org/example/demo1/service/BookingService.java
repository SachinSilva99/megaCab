package org.example.demo1.service;

import org.example.demo1.dto.request.BookingRequestDTO;
import org.example.demo1.dto.response.BookingDetailsResponseDTO;
import org.example.demo1.dto.response.BookingResponseDTO;
import org.example.demo1.util.ResponseDTO;

import java.util.List;

/**
 * Author : SachinSilva
 */
public interface BookingService extends SuperService {
    ResponseDTO<BookingResponseDTO> createBooking(BookingRequestDTO requestDTO);

    ResponseDTO<List<BookingDetailsResponseDTO>> allBookings();
}
