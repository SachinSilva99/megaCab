package com.sachin.service;

import com.sachin.dto.request.BookingRequestDTO;
import com.sachin.dto.response.BookingDetailsResponseDTO;
import com.sachin.dto.response.BookingResponseDTO;
import com.sachin.util.ResponseDTO;

import java.util.List;

/**
 * Author : SachinSilva
 */
public interface BookingService extends SuperService {
    ResponseDTO<BookingResponseDTO> createBooking(BookingRequestDTO requestDTO);

    ResponseDTO<List<BookingDetailsResponseDTO>> allBookings();
}
