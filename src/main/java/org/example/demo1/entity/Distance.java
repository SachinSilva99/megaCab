package org.example.demo1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Distance {
    private Integer id;
    private String pickupLocation;
    private String dropLocation;
    private Double distanceKm;
}
