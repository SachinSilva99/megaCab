package com.sachin.service.impl;

import com.sachin.dto.request.DistanceRequestDTO;
import com.sachin.entity.Distance;
import com.sachin.exception.AppException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import com.sachin.dto.response.DistanceResponseDTO;
import com.sachin.repository.db.DBConnection;
import com.sachin.repository.repo.DistanceRepository;
import com.sachin.service.DistanceService;
import com.sachin.util.Mapper;
import com.sachin.util.ResponseDTO;

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
            throw new AppException(e.getMessage());
        }
    }

    @Override
    public ResponseDTO<DistanceResponseDTO> createDistance(DistanceRequestDTO distanceRequestDTO) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Distance distance = Mapper.toDistance(distanceRequestDTO);

            Distance saved = distanceRepository.save(connection, Distance.class, "distance", "id", distance);
            connection.close();
            return ResponseDTO.success(Mapper.toDistanceResponseDTO(saved));
        } catch (Exception e) {
            throw new AppException(e.getMessage());
        }
    }
}
