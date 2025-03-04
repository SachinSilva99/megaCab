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
public class LoginResponseDTO {
    private String accessToken;
    private String expTime;
}
