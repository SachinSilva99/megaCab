package org.example.demo1.controller;

import org.example.demo1.annotations.RequestMapping;
import org.example.demo1.dto.response.CarResponseDTO;
import org.example.demo1.service.ServiceFactory;
import org.example.demo1.service.ServiceType;
import org.example.demo1.service.CarService;
import org.example.demo1.util.ResponseDTO;

import java.sql.SQLException;
import java.util.List;

/**
 * Author : SachinSilva
 */

@RequestMapping("/cars")
public class CarController {

    private final CarService carService = ServiceFactory.getInstance().getService(ServiceType.CAR);

    @RequestMapping(value = "/available", method = RequestMapping.HttpMethod.GET)
    public ResponseDTO<List<CarResponseDTO>> getAvailableCars() {
         return carService.getAvailableCars();
    }
}
