package org.example.demo1.dto.request;

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
public class DriverRequestDTO {
    private int id;
    private String name;
    private String phone;
}
