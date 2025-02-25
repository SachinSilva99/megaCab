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
@Builder
@NoArgsConstructor
public class BookingResponseDTO {
    private String bookingReference;
}
