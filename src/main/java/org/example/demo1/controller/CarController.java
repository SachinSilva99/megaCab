package org.example.demo1.controller;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.example.demo1.annotations.RequestMapping;
import org.example.demo1.dto.request.CarRequestDTO;
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
