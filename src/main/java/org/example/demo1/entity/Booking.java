package org.example.demo1.entity;

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
    private Integer pickupLocation;
    private Integer dropLocation;
    private Double netAmount;
    private Double totalAmount;
    private Double taxAmount;
    private String status;
    private Integer customerId;
    private LocalDateTime createdAt;
}