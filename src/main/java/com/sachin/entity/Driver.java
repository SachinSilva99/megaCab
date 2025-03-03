package com.sachin.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author : SachinSilva
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Driver {
    private int id;
    private String name;
    private String phone;
    private String status;
}
