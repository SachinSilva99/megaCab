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
public class UserRequestDTO {
    private String username;
    private String password;
    private String role;

    private String registrationNumber;
    private String name;
    private String address;
    private String nic;
    private String phone;
}
