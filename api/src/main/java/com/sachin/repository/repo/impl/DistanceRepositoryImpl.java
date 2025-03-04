package com.sachin.repository.repo.impl;

import jakarta.enterprise.context.ApplicationScoped;
import com.sachin.entity.Distance;
import com.sachin.repository.repo.DistanceRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Author : SachinSilva
 */
@ApplicationScoped
public class DistanceRepositoryImpl extends CrudRepoImpl implements DistanceRepository {

    @Override
    public List<Distance> getDistances(Connection connection){
        try {
            return executeQueryList("SELECT * FROM distance",connection,Distance.class);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    };
}
