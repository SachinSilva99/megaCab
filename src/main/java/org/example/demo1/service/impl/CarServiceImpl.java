package org.example.demo1.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.example.demo1.dto.response.CarResponseDTO;
import org.example.demo1.exception.AppException;
import org.example.demo1.repository.repo.CarRepository;
import org.example.demo1.repository.repo.db.DBConnection;
import org.example.demo1.service.CarService;
import org.example.demo1.util.ResponseDTO;

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
            return ResponseDTO.success(availableCars);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException("Error getting available cars");
        }
    }
}
