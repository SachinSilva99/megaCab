package org.example.demo1.repository.repo.impl;

import jakarta.enterprise.context.ApplicationScoped;
import org.example.demo1.dto.response.BookingDetailsResponseDTO;
import org.example.demo1.entity.Booking;
import org.example.demo1.repository.repo.BookingRepository;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class BookingRepositoryImpl extends CrudRepoImpl implements BookingRepository {
    @Override
    public Booking save(Connection connection, Booking booking) {
        try {
            return save(connection, Booking.class, "booking", "id", booking);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }


    }

    @Override
    public Optional<Booking> findByBookingNumber(Connection connection, String bookingNumber) {
        try {
            Booking booking = executeQuery("SELECT * FROM booking b WHERE b.bookingNumber = ?", connection, Booking.class, bookingNumber);
            return Optional.of(booking);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<BookingDetailsResponseDTO> getAllBookings(Connection connection) {
        try {
            return executeQueryList("""
                    SELECT * FROM booking b
                     INNER JOIN car c ON c.id = b.carId
                      INNER JOIN distance d ON d.id = b.distanceId
                    """, connection, BookingDetailsResponseDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
