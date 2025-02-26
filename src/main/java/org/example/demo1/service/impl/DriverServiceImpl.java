package org.example.demo1.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.example.demo1.dto.response.DriverResponseDTO;
import org.example.demo1.repository.db.DBConnection;
import org.example.demo1.repository.repo.DriverRepository;
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
}
