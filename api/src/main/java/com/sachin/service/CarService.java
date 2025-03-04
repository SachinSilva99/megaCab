package com.sachin.service;

import com.sachin.dto.request.CarRequestDTO;
import com.sachin.dto.response.CarResponseDTO;
import com.sachin.util.ResponseDTO;

import java.util.List;

/**
 * Author : SachinSilva
 */
public interface CarService extends SuperService{

    ResponseDTO<List<CarResponseDTO>> getAvailableCars();

    ResponseDTO<CarResponseDTO> saveCar(CarRequestDTO car);

    ResponseDTO<CarResponseDTO> updateCar(CarRequestDTO car);
}
