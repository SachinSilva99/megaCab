package org.example.demo1.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Booking {
    private Integer id;
    private String bookingNumber;
    private int distanceId;
    private Double netAmount;
    private Double totalAmount;
    private Double taxAmount;
    private String status;
    private Integer customerId;
    private Integer carId;
    private LocalDateTime createdAt;
}