package org.example.demo1.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Booking {
    private Integer id;
    private String bookingNumber;
    private String pickupLocation;
    private String dropLocation;
    private Double netAmount;
    private Double totalAmount;
    private Double discount;
    private String status;
    private Integer customerId;
    private LocalDateTime createdAt;
}