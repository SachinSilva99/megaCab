package org.example.demo1.service.impl;

import org.example.demo1.dto.response.CarResponseDTO;
import org.example.demo1.exception.AppException;
import org.example.demo1.repository.RepositoryFactory;
import org.example.demo1.repository.RepositoryType;
import org.example.demo1.repository.repo.BookingRepository;
import org.example.demo1.repository.repo.CarRepository;
import org.example.demo1.repository.repo.db.DBConnection;
import org.example.demo1.service.CarService;
import org.example.demo1.util.ResponseDTO;

import java.sql.Connection;
import java.util.List;

/**
 * Author : SachinSilva
 */
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository = RepositoryFactory.getInstance().getRepo(RepositoryType.CAR);

    @Override
    public ResponseDTO<List<CarResponseDTO>> getAvailableCars() {
        try (Connection connection = DBConnection.getInstance().getConnection()) {
            List<CarResponseDTO> availableCars = carRepository.getAvailableCars(connection);
            return ResponseDTO.success(availableCars);
        }catch (Exception e) {
            e.printStackTrace();
            throw new AppException("Error getting available cars");
        }
    }
}
