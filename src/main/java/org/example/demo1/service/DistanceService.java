package org.example.demo1.service;

import org.example.demo1.dto.response.DistanceResponseDTO;
import org.example.demo1.util.ResponseDTO;

import java.util.List;

/**
 * Author : SachinSilva
 */
public interface DistanceService {
    ResponseDTO<List<DistanceResponseDTO>> getAllDistances();
}
