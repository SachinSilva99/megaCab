package com.sachin.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author : SachinSilva
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class DistanceResponseDTO {
    private int id;
    private String pickupLocation;
    private String dropLocation;
    private Double distanceKm;
}
