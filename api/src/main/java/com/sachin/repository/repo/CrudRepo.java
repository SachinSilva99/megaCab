package com.sachin.repository.repo;

import com.sachin.repository.SuperRepository;

import java.sql.Connection;
import java.sql.SQLException;

public interface CrudRepo extends SuperRepository {

    <T> T save(Connection connection, Class<T> entityType, String tableName, String idColumnName, T entity) throws IllegalAccessException, SQLException;

    <T> T findById(Object id, Connection connection, Class<T> entityType, String idColumnName) throws SQLException;

    <T> T update(Connection connection, Class<T> entityType, String tableName, String idColumnName, T entity) throws IllegalAccessException, SQLException;
}