package com.sachin.controller;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import com.sachin.annotations.RequestBody;
import com.sachin.annotations.RequestMapping;
import com.sachin.dto.request.DriverRequestDTO;
import com.sachin.dto.response.DriverResponseDTO;
import com.sachin.service.DriverService;
import com.sachin.util.ResponseDTO;

import java.util.List;

/**
 * Author : SachinSilva
 */
@ApplicationScoped
@RequestMapping("/drivers")
public class DriverController {
    @Inject
    private DriverService driverService;

    @RequestMapping(value = "/all", method = RequestMapping.HttpMethod.GET)
    public ResponseDTO<List<DriverResponseDTO>> getAllDrivers() {
        return driverService.getAllDrivers();
    }

    @RequestMapping(method = RequestMapping.HttpMethod.POST)
    public ResponseDTO<DriverResponseDTO> createDriver(@RequestBody DriverRequestDTO driverRequestDTO) {
        return driverService.createDriver(driverRequestDTO);
    }

    @RequestMapping(method = RequestMapping.HttpMethod.PUT)
    public ResponseDTO<DriverResponseDTO> updateDriver(@RequestBody DriverRequestDTO driverRequestDTO) {
        return driverService.updateDriver(driverRequestDTO);
    }
}
