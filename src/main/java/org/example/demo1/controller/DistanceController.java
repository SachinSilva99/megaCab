package org.example.demo1.controller;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.example.demo1.annotations.RequestMapping;
import org.example.demo1.dto.response.DistanceResponseDTO;
import org.example.demo1.dto.response.DriverResponseDTO;
import org.example.demo1.service.DistanceService;
import org.example.demo1.util.ResponseDTO;

import java.util.List;

/**
 * Author : SachinSilva
 */
@ApplicationScoped
@RequestMapping("/distances")
public class DistanceController {

    @Inject
    private DistanceService distanceService;

    @RequestMapping(value = "/all", method = RequestMapping.HttpMethod.GET)
    public ResponseDTO<List<DistanceResponseDTO>> getDistances() {

        return distanceService.getAllDistances();
    }
}
