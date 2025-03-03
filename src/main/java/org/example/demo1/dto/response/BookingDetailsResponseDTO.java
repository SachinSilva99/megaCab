package org.example.demo1.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Author : SachinSilva
 */
@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class BookingDetailsResponseDTO {
    private Integer id;
    private String bookingNumber;
    private int distanceId;
    private Double netAmount;
    private Double totalAmount;
    private Double taxAmount;
    private String status;
    private Integer customerId;
    private Integer carId;
    private Timestamp createdAt;
    private String model;
    private String plateNumber;
    private Integer driverId;
    private Integer noOfSeats;
    private String image1;
    private String image2;
    private String image3;
    private String pickupLocation;
    private String dropLocation;
    private Double distanceKm;
}
