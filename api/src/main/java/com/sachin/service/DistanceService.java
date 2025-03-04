package com.sachin.service;

import com.sachin.dto.request.DistanceRequestDTO;
import com.sachin.dto.response.DistanceResponseDTO;
import com.sachin.util.ResponseDTO;

import java.util.List;

/**
 * Author : SachinSilva
 */
public interface DistanceService {

    ResponseDTO<List<DistanceResponseDTO>> getAllDistances();

    ResponseDTO<DistanceResponseDTO> createDistance(DistanceRequestDTO distanceRequestDTO);
}
