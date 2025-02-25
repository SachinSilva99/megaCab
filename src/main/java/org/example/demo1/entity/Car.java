package org.example.demo1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author : SachinSilva
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Car {
    private Integer id;
    private String model;
    private String plateNumber;
    private Integer driverId;
    private Integer noOfSeats;
}
