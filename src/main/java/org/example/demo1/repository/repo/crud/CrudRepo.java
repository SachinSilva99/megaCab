package org.example.demo1.repository.repo.crud;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface CrudRepo<T, ID> {
    T findById(ID id, Connection connection) throws SQLException;

    List<T> findAll(Connection connection) throws SQLException;

    void save(T entity, Connection connection) throws SQLException, IllegalAccessException;

    void update(T entity, Connection connection) throws SQLException, IllegalAccessException, NoSuchFieldException;

    void deleteById(ID id, Connection connection) throws SQLException;
}