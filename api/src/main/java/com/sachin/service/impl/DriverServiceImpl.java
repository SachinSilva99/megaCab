package com.sachin.service.impl;

import com.sachin.dto.other.HeaderHolder;
import com.sachin.entity.User;
import com.sachin.service.UserService;
import com.sachin.util.UtilMethods;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import com.sachin.dto.request.DriverRequestDTO;
import com.sachin.dto.response.DriverResponseDTO;
import com.sachin.entity.Driver;
import com.sachin.exception.AppException;
import com.sachin.repository.db.DBConnection;
import com.sachin.repository.repo.DriverRepository;
import com.sachin.repository.transaction.TransactionManager;
import com.sachin.service.DriverService;
import com.sachin.util.Mapper;
import com.sachin.util.ResponseDTO;
import lombok.Data;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Author : SachinSilva
 */
@ApplicationScoped
@Data
public class DriverServiceImpl implements DriverService {

    @Inject
    private DriverRepository driverRepository;
    @Inject
    private UserService userService;
    @Inject
    private HeaderHolder headerHolder;

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
            User userLoggedInUser = userService.getUserLoggedInUser();
            boolean admin = UtilMethods.isAdmin(userLoggedInUser);
            if (!admin) {
                throw new AppException("You do not have permission to update this Driver");
            }
            DriverResponseDTO driverResponseDTO = TransactionManager.executeTransaction(connection -> {
                Driver driver = Mapper.toDriver(driverRequestDTO);
                driver.setStatus("AVAILABLE");
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
            User userLoggedInUser = userService.getUserLoggedInUser();
            boolean admin = UtilMethods.isAdmin(userLoggedInUser);
            if (!admin) {
                throw new AppException("You do not have permission to update this Driver");
            }
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
