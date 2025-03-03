package org.example.demo1.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.example.demo1.dto.request.CarRequestDTO;
import org.example.demo1.dto.response.CarResponseDTO;
import org.example.demo1.entity.Car;
import org.example.demo1.exception.AppException;
import org.example.demo1.repository.repo.CarRepository;
import org.example.demo1.repository.db.DBConnection;
import org.example.demo1.service.CarService;
import org.example.demo1.util.Mapper;
import org.example.demo1.util.ResponseDTO;

import java.sql.Connection;
import java.sql.SQLException;
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
