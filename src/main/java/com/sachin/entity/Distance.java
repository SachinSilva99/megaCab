package com.sachin.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Distance {
    private Integer id;
    private String pickupLocation;
    private String dropLocation;
    private Double distanceKm;
}
