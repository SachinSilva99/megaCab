package org.example.demo1.repository.repo.crud;

import org.example.demo1.repository.SuperRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface CrudRepo extends SuperRepository {
    <R> R executeQuery(String sql, Connection connection, Class<R> resultType, Object... params) throws SQLException;

    <L> List<L> executeQueryList(String sql, Connection connection, Class<L> resultType, Object... params) throws SQLException;

    int executeUpdate(String sql, Connection connection, Object... params) throws SQLException;

    int executeDelete(String sql, Connection connection, Object... params) throws SQLException;

    public <T> T executeSave(Connection connection, Class<T> entityType,String tableName, String idColumnName, T entity) throws IllegalAccessException, SQLException;
}