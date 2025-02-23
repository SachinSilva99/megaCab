package org.example.demo1.repository.repo;

import org.example.demo1.repository.SuperRepository;
import org.example.demo1.repository.entity.Booking;

import java.sql.Connection;
import java.util.Optional;

public interface BookingRepository extends SuperRepository {

    Booking save(Connection connection, Booking booking);

    Optional<Booking> findByBookingNumber(Connection connection,String bookingNumber);
}
