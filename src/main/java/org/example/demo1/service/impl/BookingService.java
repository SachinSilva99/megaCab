package org.example.demo1.service.impl;


import org.example.demo1.repository.RepositoryFactory;
import org.example.demo1.repository.RepositoryType;
import org.example.demo1.repository.entity.Booking;
import org.example.demo1.repository.entity.Customer;
import org.example.demo1.repository.repo.BookingRepository;
import org.example.demo1.repository.repo.CustomerRepository;
import org.example.demo1.repository.repo.db.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.UUID;

public class BookingService {
    private final CustomerRepository customerRepository = RepositoryFactory.getInstance().getRepo(RepositoryType.CUSTOMER);
    private final BookingRepository bookingRepository = RepositoryFactory.getInstance().getRepo(RepositoryType.BOOKING);


    public Booking createBooking(String nic, String pickup, String drop, Double fare) {
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Customer customer = customerRepository.findByNic(connection, nic)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Booking booking = new Booking();
        booking.setBookingNumber(UUID.randomUUID().toString());
        booking.setCustomerId(customer.getId());
        booking.setPickupLocation(pickup);
        booking.setDropLocation(drop);
        booking.setNetAmount(fare);
        booking.setCreatedAt(LocalDateTime.now());

        return bookingRepository.save(connection, booking);
    }

    public Booking getBooking(String bookingNumber) {
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bookingRepository.findByBookingNumber(connection, bookingNumber)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
    }
}
