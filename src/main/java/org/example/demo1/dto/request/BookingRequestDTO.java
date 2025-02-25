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
public class BookingRequestDTO {
    private Integer pickUpLocationId;
    private Integer dropLocationId;
    private int carId;
    private Double netAmount;
    private Double totalAmount;
    private Double taxAmount;
    private Integer customerId;
}
