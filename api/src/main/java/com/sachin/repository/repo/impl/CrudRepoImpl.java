package com.sachin.repository.repo.impl;


import com.sachin.repository.repo.CrudRepo;

import java.lang.reflect.Field;
import java.math.BigInteger;
import java.sql.*;
import java.util.*;

public class CrudRepoImpl implements CrudRepo {
    @SuppressWarnings("unchecked")
     <R> R executeQuery(String sql, Connection connection, Class<R> resultType, Object... params) throws SQLException {
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            setQueryParameters(pstmt, params);
            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    return (mapResultSetToEntity(rs, resultType));
                }
            }
        }
        return null;
    }

     <L> List<L> executeQueryList(String sql, Connection connection, Class<L> resultType, Object... params) throws SQLException {
        List<L> results = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            setQueryParameters(pstmt, params);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    results.add(mapResultSetToEntity(rs, resultType));
                }
            }
        }
        return results;
    }


     int executeUpdate(String sql, Connection connection, Object... params) throws SQLException {
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            setQueryParameters(pstmt, params);
            return pstmt.executeUpdate();
        }
    }


     int executeDelete(String sql, Connection connection, Object... params) throws SQLException {
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            setQueryParameters(pstmt, params);
            return pstmt.executeUpdate();
        }
    }



    @Override
    public <T> T save(Connection connection, Class<T> entityType, String tableName, String idColumnName, T entity) throws IllegalAccessException, SQLException {
        StringBuilder sql = new StringBuilder("INSERT INTO " + tableName + " (");
        StringBuilder values = new StringBuilder("VALUES (");
        List<Object> paramValues = new ArrayList<>();

        Field[] fields = entityType.getDeclaredFields();
        for (Field field : fields) {
            if (!field.getName().equalsIgnoreCase(idColumnName)) {
                field.setAccessible(true);
                String columnName = field.getName();
                sql.append(columnName).append(", ");
                values.append("?, ");
                paramValues.add(field.get(entity));
            }
        }
        sql.setLength(sql.length() - 2);
        values.setLength(values.length() - 2);
        sql.append(") ").append(values).append(")");

        try (PreparedStatement pstmt = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS)) {
            setQueryParameters(pstmt, paramValues.toArray());
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        Object key = generatedKeys.getObject(1);
                        Integer generatedId;

                        if (key instanceof BigInteger) {
                            generatedId = ((BigInteger) key).intValue(); // Convert BigInteger to Integer
                        } else if (key instanceof Long) {
                            generatedId = ((Long) key).intValue(); // Convert Long to Integer if needed
                        } else {
                            generatedId = (Integer) key; // Direct cast if it's already an Integer
                        }

                        Field idField = entityType.getDeclaredField(idColumnName);
                        idField.setAccessible(true);
                        idField.set(entity, generatedId);
                    }
                } catch (NoSuchFieldException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return entity;
    }

    public <T> T findById(Object id, Connection connection, Class<T> entityType, String idColumnName) {
        String sql = "SELECT * FROM " + entityType.getSimpleName() + " WHERE " + idColumnName + " = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setObject(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToEntity(rs, entityType);
                }
            }
        } catch (SQLException e) {
            System.out.println("========== Error");
            e.printStackTrace(); // Or use a logger for better error handling
        }
        return null;
    }


    private void setQueryParameters(PreparedStatement pstmt, Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            pstmt.setObject(i + 1, params[i]);
        }
    }

    private <R> R mapResultSetToEntity(ResultSet rs, Class<R> resultType) throws SQLException {
        try {
            R entity = resultType.getDeclaredConstructor().newInstance();
            Field[] fields = resultType.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String columnName =  field.getName();
                field.set(entity, rs.getObject(columnName));
            }
            return entity;
        } catch (Exception e) {
            throw new SQLException("Failed to map ResultSet to entity", e);
        }
    }

    @Override
    public <T> T update(Connection connection, Class<T> entityType, String tableName, String idColumnName, T entity) throws IllegalAccessException, SQLException {
        StringBuilder sql = new StringBuilder("UPDATE " + tableName + " SET ");
        List<Object> paramValues = new ArrayList<>();
        Object idValue = null;

        Field[] fields = entityType.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object value = field.get(entity);
            if (field.getName().equalsIgnoreCase(idColumnName)) {
                idValue = value;
            } else {
                sql.append(field.getName()).append(" = ?, ");
                paramValues.add(value);
            }
        }

        if (idValue == null) {
            throw new IllegalArgumentException("ID field cannot be null for update");
        }

        sql.setLength(sql.length() - 2);
        sql.append(" WHERE ").append(idColumnName).append(" = ?");
        paramValues.add(idValue);

        try (PreparedStatement pstmt = connection.prepareStatement(sql.toString())) {
            setQueryParameters(pstmt, paramValues.toArray());
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating entity failed, no rows affected.");
            }
        }

        return entity;
    }


}
