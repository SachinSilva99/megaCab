package org.example.demo1.service;

import org.example.demo1.dto.request.CarRequestDTO;
import org.example.demo1.dto.response.CarResponseDTO;
import org.example.demo1.util.ResponseDTO;

import java.util.List;

/**
 * Author : SachinSilva
 */
public interface CarService extends SuperService{
    ResponseDTO<List<CarResponseDTO>> getAvailableCars();

    ResponseDTO<CarResponseDTO> saveCar(CarRequestDTO car);

    ResponseDTO<CarResponseDTO> updateCar(CarRequestDTO car);
}
