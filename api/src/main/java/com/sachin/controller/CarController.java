package com.sachin.controller;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import com.sachin.annotations.RequestMapping;
import com.sachin.dto.request.CarRequestDTO;
import com.sachin.dto.response.CarResponseDTO;
import com.sachin.service.CarService;
import com.sachin.util.ResponseDTO;

import java.util.List;

/**
 * Author : SachinSilva
 */
@RequestMapping("/cars")
@ApplicationScoped
public class CarController {

    @Inject
    private CarService carService;

    @RequestMapping(value = "/available", method = RequestMapping.HttpMethod.GET)
    public ResponseDTO<List<CarResponseDTO>> getAvailableCars() {
        ResponseDTO<List<CarResponseDTO>> availableCars = carService.getAvailableCars();
        return availableCars;
    }

    @RequestMapping( method = RequestMapping.HttpMethod.POST)
    public ResponseDTO<CarResponseDTO> saveCar(@RequestMapping CarRequestDTO car) {
        return carService.saveCar(car);
    }


    @RequestMapping( method = RequestMapping.HttpMethod.PUT)
    public ResponseDTO<CarResponseDTO> updateCar(@RequestMapping CarRequestDTO car) {
        return carService.updateCar(car);
    }
}
