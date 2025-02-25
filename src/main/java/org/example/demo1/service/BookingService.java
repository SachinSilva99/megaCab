package org.example.demo1.service;

import org.example.demo1.dto.request.BookingRequestDTO;
import org.example.demo1.dto.response.BookingResponseDTO;
import org.example.demo1.util.ResponseDTO;

/**
 * Author : SachinSilva
 */
public interface BookingService extends SuperService {
    ResponseDTO<BookingResponseDTO> createBooking(BookingRequestDTO requestDTO);
}
