package org.example.demo1.repository.repo;

import org.example.demo1.entity.Booking;
import org.example.demo1.repository.crud.CrudRepo;

import java.sql.Connection;
import java.util.Optional;

public interface BookingRepository extends CrudRepo {

    Booking save(Connection connection, Booking booking);

    Optional<Booking> findByBookingNumber(Connection connection,String bookingNumber);
}
