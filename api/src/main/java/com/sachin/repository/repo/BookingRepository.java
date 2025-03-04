package com.sachin.repository.repo;

import com.sachin.dto.response.BookingDetailsResponseDTO;
import com.sachin.entity.Booking;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends CrudRepo {

    Booking save(Connection connection, Booking booking);

    Optional<Booking> findByBookingNumber(Connection connection,String bookingNumber);

    List<BookingDetailsResponseDTO> getAllBookings(Connection connection);
}
