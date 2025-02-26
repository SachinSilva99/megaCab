package org.example.demo1.repository.repo.impl;

import jakarta.enterprise.context.ApplicationScoped;
import org.example.demo1.entity.Distance;
import org.example.demo1.repository.crud.CrudRepoImpl;
import org.example.demo1.repository.db.DBConnection;
import org.example.demo1.repository.repo.DistanceRepository;

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
