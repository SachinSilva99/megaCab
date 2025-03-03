package com.sachin.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author : SachinSilva
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DistanceRequestDTO {
    private int id;
    private String pickupLocation;
    private String dropLocation;
    private Double distanceKm;
}
