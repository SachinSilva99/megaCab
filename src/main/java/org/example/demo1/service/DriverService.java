package org.example.demo1.service;

import org.example.demo1.dto.request.DriverRequestDTO;
import org.example.demo1.dto.response.DriverResponseDTO;
import org.example.demo1.util.ResponseDTO;

import java.sql.Connection;
import java.util.List;

/**
 * Author : SachinSilva
 */
public interface DriverService extends SuperService {
    ResponseDTO<List<DriverResponseDTO>> getAllDrivers();

    ResponseDTO<DriverResponseDTO> createDriver(DriverRequestDTO driverRequestDTO);

    ResponseDTO<DriverResponseDTO> updateDriver(DriverRequestDTO driverRequestDTO);
}
