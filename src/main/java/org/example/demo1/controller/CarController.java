package org.example.demo1.controller;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.example.demo1.annotations.RequestMapping;
import org.example.demo1.dto.response.CarResponseDTO;
import org.example.demo1.service.CarService;
import org.example.demo1.util.ResponseDTO;

import java.util.List;

/**
 * Author : SachinSilva
 */
@RequestMapping("/cars")
@ApplicationScoped
public class CarController {

    @Inject
    private  CarService carService;

    @RequestMapping(value = "/available", method = RequestMapping.HttpMethod.GET)
    public ResponseDTO<List<CarResponseDTO>> getAvailableCars() {
         return carService.getAvailableCars();
    }
}
