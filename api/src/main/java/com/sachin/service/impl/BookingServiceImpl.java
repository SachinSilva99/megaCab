package com.sachin.service.impl;


import com.sachin.entity.Distance;
import com.sachin.repository.repo.DistanceRepository;
import com.sachin.service.UserService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import com.sachin.dto.request.BookingRequestDTO;
import com.sachin.dto.response.BookingDetailsResponseDTO;
import com.sachin.dto.response.BookingResponseDTO;
import com.sachin.entity.Booking;
import com.sachin.entity.Customer;
import com.sachin.repository.repo.BookingRepository;
import com.sachin.repository.repo.CustomerRepository;
import com.sachin.repository.db.DBConnection;
import com.sachin.repository.transaction.TransactionManager;
import com.sachin.service.BookingService;
import com.sachin.util.ResponseDTO;

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
    private DistanceRepository distanceRepository;
    @Inject
    private BookingRepository bookingRepository;
    @Inject
    private UserService userService;


    @Override
    public ResponseDTO<BookingResponseDTO> createBooking(BookingRequestDTO requestDTO) {
        userService.getUserLoggedInUser();
        return TransactionManager.executeTransaction(connection -> {
            Integer customerId = requestDTO.getCustomerId();
            int distanceId = requestDTO.getDistanceId();
            Distance distance = distanceRepository.findById(distanceId, connection, Distance.class, "id");
            Double distanceKm = distance.getDistanceKm();
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
