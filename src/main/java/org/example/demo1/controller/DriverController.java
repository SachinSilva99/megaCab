package org.example.demo1.controller;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.example.demo1.annotations.RequestBody;
import org.example.demo1.annotations.RequestMapping;
import org.example.demo1.dto.request.DriverRequestDTO;
import org.example.demo1.dto.response.DriverResponseDTO;
import org.example.demo1.entity.User;
import org.example.demo1.service.DriverService;
import org.example.demo1.util.ResponseDTO;

import java.sql.SQLException;
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
