package org.example.demo1.dto.response;

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
public class CarResponseDTO {
    private Integer id;
    private String model;
    private Integer driverId;
    private String driverName;
    private String driverContact;
    private Integer noOfSeats;
}
