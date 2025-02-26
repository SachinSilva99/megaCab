package org.example.demo1.dto.other;

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
public class HeaderHolder {
    private String token;
}
