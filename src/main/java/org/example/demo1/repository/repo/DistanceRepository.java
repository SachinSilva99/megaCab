package org.example.demo1.repository.repo;

import org.example.demo1.entity.Distance;
import org.example.demo1.repository.crud.CrudRepo;

import java.sql.Connection;
import java.util.List;

/**
 * Author : SachinSilva
 */
public interface DistanceRepository extends CrudRepo {
    List<Distance> getDistances(Connection connection);
}
