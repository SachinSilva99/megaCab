package org.example.demo1.repository.repo.crud;

import org.example.demo1.repository.annotations.ColumnName;
import org.example.demo1.repository.annotations.Param;
import org.example.demo1.repository.annotations.Query;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.sql.*;
import java.util.*;

public class CrudRepoImpl<T, ID> implements CrudRepo<T, ID> {

    private final Class<T> entityType;
    private final String tableName;
    private final String idColumnName;

    public CrudRepoImpl(Class<T> entityType, String tableName, String idColumnName) {
        this.entityType = entityType;
        this.tableName = tableName;
        this.idColumnName = idColumnName;
    }

    @Override
    public List<T> findAll(Connection connection) throws SQLException {
        List<T> entities = new ArrayList<>();
        String sql = "SELECT * FROM " + tableName;

        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                entities.add(mapResultSetToEntity(rs));
            }
        }
        return entities;
    }

    @Override
    public T findById(ID id, Connection connection) throws SQLException {
        String sql = "SELECT * FROM " + tableName + " WHERE " + idColumnName + " = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setObject(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToEntity(rs);
                }
            }
        }
        return null;
    }

    @Override
    public void save(T entity, Connection connection) throws SQLException, IllegalAccessException {
        StringBuilder sql = new StringBuilder("INSERT INTO " + tableName + " (");
        StringBuilder values = new StringBuilder("VALUES (");
        List<Object> paramValues = new ArrayList<>();

        Field[] fields = entityType.getDeclaredFields();
        for (Field field : fields) {
            if (!field.getName().equalsIgnoreCase(idColumnName)) {
                field.setAccessible(true);
                ColumnName columnAnnotation = field.getAnnotation(ColumnName.class);
                String columnName = (columnAnnotation != null) ? columnAnnotation.value() : field.getName();
                sql.append(columnName).append(", ");
                values.append("?, ");
                paramValues.add(field.get(entity));
            }
        }
        sql.setLength(sql.length() - 2); // Remove last comma
        values.setLength(values.length() - 2); // Remove last comma
        sql.append(") ").append(values).append(")");

        try (PreparedStatement pstmt = connection.prepareStatement(sql.toString())) {
            setQueryParameters(pstmt, paramValues.toArray());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void update(T entity, Connection connection) throws SQLException, IllegalAccessException, NoSuchFieldException {
        StringBuilder sql = new StringBuilder("UPDATE " + tableName + " SET ");
        List<Object> paramValues = new ArrayList<>();

        Field[] fields = entityType.getDeclaredFields();
        for (Field field : fields) {
            if (!field.getName().equalsIgnoreCase(idColumnName)) {
                field.setAccessible(true);
                ColumnName columnAnnotation = field.getAnnotation(ColumnName.class);
                String columnName = (columnAnnotation != null) ? columnAnnotation.value() : field.getName();
                sql.append(columnName).append(" = ?, ");
                paramValues.add(field.get(entity));
            }
        }
        sql.setLength(sql.length() - 2); // Remove last comma
        sql.append(" WHERE ").append(idColumnName).append(" = ?");

        Field idField = entityType.getDeclaredField(idColumnName);
        idField.setAccessible(true);
        paramValues.add(idField.get(entity));

        try (PreparedStatement pstmt = connection.prepareStatement(sql.toString())) {
            setQueryParameters(pstmt, paramValues.toArray());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void deleteById(ID id, Connection connection) throws SQLException {
        String sql = "DELETE FROM " + tableName + " WHERE " + idColumnName + " = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setObject(1, id);
            pstmt.executeUpdate();
        }
    }

    /**
     * Generic method to execute a query returning a list of entities.
     */
    public List<T> executeQuery(String sql, Connection connection, Object... params) throws SQLException {
        List<T> results = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            setQueryParameters(pstmt, params);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    results.add(mapResultSetToEntity(rs));
                }
            }
        }
        return results;
    }

    /**
     * Generic method to execute an update operation.
     */
    public int executeUpdate(String sql, Connection connection, Object... params) throws SQLException {
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            setQueryParameters(pstmt, params);
            return pstmt.executeUpdate();
        }
    }

    /**
     * Maps a ResultSet row to an entity instance.
     */
    private T mapResultSetToEntity(ResultSet rs) throws SQLException {
        try {
            T entity = entityType.getDeclaredConstructor().newInstance();
            Field[] fields = entityType.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                ColumnName columnAnnotation = field.getAnnotation(ColumnName.class);
                String columnName = (columnAnnotation != null) ? columnAnnotation.value() : field.getName();
                field.set(entity, rs.getObject(columnName));
            }
            return entity;
        } catch (Exception e) {
            throw new SQLException("Failed to map ResultSet to entity", e);
        }
    }

    /**
     * Set parameters dynamically in PreparedStatement.
     */
    private void setQueryParameters(PreparedStatement pstmt, Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            pstmt.setObject(i + 1, params[i]);
        }
    }
    // New method to handle dynamic queries with annotations for any repository
    public T findByQuery(Method method, Connection connection, Object... methodArgs) throws SQLException, NoSuchMethodException {
        Query queryAnnotation = method.getAnnotation(Query.class);
        if (queryAnnotation != null) {
            String sql = queryAnnotation.value(); // Extract SQL from annotation

            // Use reflection to find @Param annotations and bind them dynamically
            Map<String, Object> paramMap = new HashMap<>();
            Parameter[] parameters = method.getParameters();

            // Loop through method parameters and find @Param annotations
            for (int i = 0; i < parameters.length; i++) {
                Param paramAnnotation = parameters[i].getAnnotation(Param.class);
                if (paramAnnotation != null) {
                    String paramName = paramAnnotation.value(); // Get the parameter name from the @Param annotation
                    // Dynamically bind the actual value from the method arguments (username, connection, etc.)
                    paramMap.put(paramName, methodArgs[i]);
                }
            }

            // Convert named parameters (e.g., :username) to ?
            sql = convertNamedParametersToQuestionMarks(sql, paramMap);

            // Execute the query using executeQuery method
            List<T> entities = executeQuery(sql, connection, paramMap.values().toArray());
            return (entities.isEmpty()) ? null : entities.get(0);
        }
        return null;
    }

    // Convert named parameters (e.g., :username) to ?
    private String convertNamedParametersToQuestionMarks(String query, Map<String, Object> paramMap) {
        for (String paramName : paramMap.keySet()) {
            query = query.replace(":" + paramName, "?");
        }
        return query;
    }
}
