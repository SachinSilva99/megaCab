package com.sachin.entity;

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
    private String image1;
    private String image2;
    private String image3;
}
