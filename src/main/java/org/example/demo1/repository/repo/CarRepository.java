package org.example.demo1.repository.repo;

import org.example.demo1.dto.response.CarResponseDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Author : SachinSilva
 */
public interface CarRepository extends CrudRepo {

    List<CarResponseDTO> getAvailableCars(Connection connection) throws SQLException;
}
