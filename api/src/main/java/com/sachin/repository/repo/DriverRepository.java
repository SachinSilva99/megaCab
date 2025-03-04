package com.sachin.repository.repo;

import com.sachin.entity.Driver;

import java.sql.Connection;
import java.util.List;

/**
 * Author : SachinSilva
 */
public interface DriverRepository extends CrudRepo {
    List<Driver> getAllDrivers(Connection connection);
}
