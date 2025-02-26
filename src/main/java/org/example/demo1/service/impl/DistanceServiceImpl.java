package org.example.demo1.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.example.demo1.dto.response.DistanceResponseDTO;
import org.example.demo1.repository.db.DBConnection;
import org.example.demo1.repository.repo.DistanceRepository;
import org.example.demo1.service.DistanceService;
import org.example.demo1.util.Mapper;
import org.example.demo1.util.ResponseDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Author : SachinSilva
 */
@ApplicationScoped
public class DistanceServiceImpl implements DistanceService {
    @Inject
    private DistanceRepository distanceRepository;

    @Override
    public ResponseDTO<List<DistanceResponseDTO>> getAllDistances() {
        try {

            Connection connection = DBConnection.getInstance().getConnection();
            List<DistanceResponseDTO> list = distanceRepository.getDistances(connection).stream().map(Mapper::toDistanceResponseDTO).toList();
            connection.close();
            return ResponseDTO.success(list);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
