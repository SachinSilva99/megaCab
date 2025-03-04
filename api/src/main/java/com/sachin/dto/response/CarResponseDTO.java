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
public class CarResponseDTO {
    private Integer id;
    private String model;
    private Integer noOfSeats;
    private String image1;
    private String image2;
    private String image3;
}
