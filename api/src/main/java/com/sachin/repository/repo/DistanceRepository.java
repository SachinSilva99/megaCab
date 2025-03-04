package com.sachin.repository.repo;

import com.sachin.entity.Distance;

import java.sql.Connection;
import java.util.List;

/**
 * Author : SachinSilva
 */
public interface DistanceRepository extends CrudRepo {
    List<Distance> getDistances(Connection connection);
}
