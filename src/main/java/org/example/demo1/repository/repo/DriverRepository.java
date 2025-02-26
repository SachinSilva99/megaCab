package org.example.demo1.repository.repo;

import org.example.demo1.entity.Driver;
import org.example.demo1.repository.crud.CrudRepo;

import java.sql.Connection;
import java.util.List;

/**
 * Author : SachinSilva
 */
public interface DriverRepository extends CrudRepo {
    List<Driver> getAllDrivers(Connection connection);
}
