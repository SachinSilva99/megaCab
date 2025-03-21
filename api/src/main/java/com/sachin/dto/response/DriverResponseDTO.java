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
@Builder
@NoArgsConstructor
public class DriverResponseDTO {
    private int id;
    private String name;
    private String phone;
}
