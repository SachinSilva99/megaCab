package com.sachin.repository.repo;

import com.sachin.dto.response.CarResponseDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Author : SachinSilva
 */
public interface CarRepository extends CrudRepo {

    List<CarResponseDTO> getAvailableCars(Connection connection) throws SQLException;
}
