package com.sachin.service;

import com.sachin.dto.request.DriverRequestDTO;
import com.sachin.dto.response.DriverResponseDTO;
import com.sachin.util.ResponseDTO;

import java.util.List;

/**
 * Author : SachinSilva
 */
public interface DriverService extends SuperService {
    ResponseDTO<List<DriverResponseDTO>> getAllDrivers();

    ResponseDTO<DriverResponseDTO> createDriver(DriverRequestDTO driverRequestDTO);

    ResponseDTO<DriverResponseDTO> updateDriver(DriverRequestDTO driverRequestDTO);
}
