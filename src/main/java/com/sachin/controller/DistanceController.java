package com.sachin.controller;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import com.sachin.annotations.RequestMapping;
import com.sachin.dto.response.DistanceResponseDTO;
import com.sachin.service.DistanceService;
import com.sachin.util.ResponseDTO;

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
