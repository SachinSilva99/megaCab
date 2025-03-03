package com.sachin.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import com.sachin.dto.request.CarRequestDTO;
import com.sachin.dto.response.CarResponseDTO;
import com.sachin.entity.Car;
import com.sachin.exception.AppException;
import com.sachin.repository.repo.CarRepository;
import com.sachin.repository.db.DBConnection;
import com.sachin.service.CarService;
import com.sachin.util.Mapper;
import com.sachin.util.ResponseDTO;

import java.sql.Connection;
import java.util.List;

/**
 * Author : SachinSilva
 */
@ApplicationScoped
public class CarServiceImpl implements CarService {
    @Inject
    private CarRepository carRepository;

    @Override
    public ResponseDTO<List<CarResponseDTO>> getAvailableCars() {
        try (Connection connection = DBConnection.getInstance().getConnection()) {
            List<CarResponseDTO> availableCars = carRepository.getAvailableCars(connection);
            connection.close();
            return ResponseDTO.success(availableCars);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException("Error getting available cars");
        }
    }

    @Override
    public ResponseDTO<CarResponseDTO> saveCar(CarRequestDTO carRequestDTO) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Car car = Mapper.toCar(carRequestDTO);

            Car saved = carRepository.save(connection, Car.class, "car", "id", car);
            CarResponseDTO carResponseDTO = Mapper.toCarResponseDTO(saved);
            connection.close();
            return ResponseDTO.success(carResponseDTO);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(e.getMessage());
        }
    }

    @Override
    public ResponseDTO<CarResponseDTO> updateCar(CarRequestDTO carRequestDTO) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Car car = Mapper.toCar(carRequestDTO);

            Car saved = carRepository.update(connection, Car.class, "car", "id", car);
            CarResponseDTO carResponseDTO = Mapper.toCarResponseDTO(saved);
            connection.close();
            return ResponseDTO.success(carResponseDTO);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(e.getMessage());
        }
    }
}
