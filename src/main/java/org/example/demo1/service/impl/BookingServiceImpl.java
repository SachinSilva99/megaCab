package org.example.demo1.service.impl;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.example.demo1.dto.request.BookingRequestDTO;
import org.example.demo1.dto.response.BookingResponseDTO;
import org.example.demo1.entity.Booking;
import org.example.demo1.entity.Customer;
import org.example.demo1.repository.repo.BookingRepository;
import org.example.demo1.repository.repo.CustomerRepository;
import org.example.demo1.repository.db.DBConnection;
import org.example.demo1.repository.transaction.TransactionManager;
import org.example.demo1.service.BookingService;
import org.example.demo1.util.ResponseDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.UUID;

@ApplicationScoped
public class BookingServiceImpl implements BookingService {
    @Inject
    private  CustomerRepository customerRepository;
    @Inject
    private  BookingRepository bookingRepository;


    @Override
    public ResponseDTO<BookingResponseDTO> createBooking(BookingRequestDTO requestDTO) {
        return TransactionManager.executeTransaction(connection -> {
            Integer customerId = requestDTO.getCustomerId();
            int distanceId = requestDTO.getDistanceId();
            Double netAmount = requestDTO.getNetAmount();
            int carId = requestDTO.getCarId();
            Double totalAmount = requestDTO.getTotalAmount();
            Double taxAmount = requestDTO.getTaxAmount();

            try {
                connection = DBConnection.getInstance().getConnection();

                Customer customer = customerRepository.findById(customerId, connection, Customer.class, "id");
                if (customer == null) throw new RuntimeException("Customer not found");

                Booking booking = Booking.builder()
                        .bookingNumber(UUID.randomUUID().toString())
                        .customerId(customer.getId())
                        .distanceId(distanceId)
                        .netAmount(netAmount)
                        .carId(carId)
                        .totalAmount(totalAmount)
                        .taxAmount(taxAmount)
                        .status("PENDING")
                        .createdAt(LocalDateTime.now())
                        .build();

                bookingRepository.save(connection, booking);
                var bookingResponseDTO = BookingResponseDTO.builder()
                        .bookingReference(UUID.randomUUID().toString())
                        .build();
                return ResponseDTO.success(bookingResponseDTO);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public Booking getBooking(String bookingNumber) {
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bookingRepository.findByBookingNumber(connection, bookingNumber)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
    }
}
