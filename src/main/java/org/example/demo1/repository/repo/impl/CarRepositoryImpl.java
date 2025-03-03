package org.example.demo1.repository.repo.impl;

import jakarta.enterprise.context.ApplicationScoped;
import org.example.demo1.dto.response.CarResponseDTO;
import org.example.demo1.repository.repo.CarRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Author : SachinSilva
 */
@ApplicationScoped
public class CarRepositoryImpl extends CrudRepoImpl implements CarRepository {
    @Override
    public List<CarResponseDTO> getAvailableCars(Connection connection) {
        try {
            String sql = """
                SELECT *
                FROM car c
                WHERE c.availability = 'AVAILABLE'
                """;
            return executeQueryList(sql,connection,CarResponseDTO.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
