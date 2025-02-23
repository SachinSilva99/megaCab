package org.example.demo1.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Customer {
    private Integer id;
    private String registrationNumber;
    private String name;
    private String address;
    private String nic;
    private String phone;
}