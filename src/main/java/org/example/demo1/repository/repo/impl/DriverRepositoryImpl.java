package org.example.demo1.repository.repo.impl;

import jakarta.enterprise.context.ApplicationScoped;
import org.example.demo1.entity.Driver;
import org.example.demo1.repository.repo.DriverRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Author : SachinSilva
 */
@ApplicationScoped
public class DriverRepositoryImpl extends CrudRepoImpl implements DriverRepository {


    @Override
    public List<Driver> getAllDrivers(Connection connection) {
        try {
            return executeQueryList("SELECT * FROM driver d WHERE d.status = 'AVAILABLE'", connection, Driver.class);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
