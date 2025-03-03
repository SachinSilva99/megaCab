package org.example.demo1.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.example.demo1.dto.request.DriverRequestDTO;
import org.example.demo1.dto.response.DriverResponseDTO;
import org.example.demo1.entity.Driver;
import org.example.demo1.exception.AppException;
import org.example.demo1.repository.db.DBConnection;
import org.example.demo1.repository.repo.DriverRepository;
import org.example.demo1.repository.transaction.TransactionManager;
import org.example.demo1.service.DriverService;
import org.example.demo1.util.Mapper;
import org.example.demo1.util.ResponseDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Author : SachinSilva
 */
@ApplicationScoped
public class DriverServiceImpl implements DriverService {

    @Inject
    private DriverRepository driverRepository;

    @Override
    public ResponseDTO<List<DriverResponseDTO>> getAllDrivers() {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            List<DriverResponseDTO> list = driverRepository
                    .getAllDrivers(connection).stream()
                    .map(Mapper::toDriverResponseDTO)
                    .toList();
            connection.close();
            return ResponseDTO.success(list);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseDTO<DriverResponseDTO> createDriver(DriverRequestDTO driverRequestDTO) {
        try {
            DriverResponseDTO driverResponseDTO = TransactionManager.executeTransaction(connection -> {
                Driver driver = Mapper.toDriver(driverRequestDTO);
                Driver saved = driverRepository.save(connection, Driver.class, "driver", "id", driver);
                return Mapper.toDriverResponseDTO(saved);
            });
            return ResponseDTO.success(driverResponseDTO);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(e.getMessage());
        }
    }

    @Override
    public ResponseDTO<DriverResponseDTO> updateDriver(DriverRequestDTO driverRequestDTO) {
        try {
            DriverResponseDTO driverResponseDTO = TransactionManager.executeTransaction(connection -> {
                Driver driver = Mapper.toDriver(driverRequestDTO);
                Driver saved = driverRepository.update(connection, Driver.class, "driver", "id", driver);
                return Mapper.toDriverResponseDTO(saved);
            });
            return ResponseDTO.success(driverResponseDTO);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(e.getMessage());
        }
    }
}
