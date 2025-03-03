package org.example.demo1.service.impl;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.example.demo1.dto.request.BookingRequestDTO;
import org.example.demo1.dto.response.BookingDetailsResponseDTO;
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
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class BookingServiceImpl implements BookingService {
    @Inject
    private CustomerRepository customerRepository;
    @Inject
    private BookingRepository bookingRepository;


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
                        .createdAt(Timestamp.from(Instant.now()))
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

    @Override
    public ResponseDTO<List<BookingDetailsResponseDTO>> allBookings() {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            List<BookingDetailsResponseDTO> list = bookingRepository.getAllBookings(connection);
            connection.close();
            return ResponseDTO.success(list);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
