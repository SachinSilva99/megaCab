package org.example.demo1.repository.repo.impl;

import org.example.demo1.exception.AppException;
import org.example.demo1.repository.annotations.Param;
import org.example.demo1.repository.annotations.Query;
import org.example.demo1.repository.entity.User;
import org.example.demo1.repository.repo.UserRepository;
import org.example.demo1.repository.repo.crud.CrudRepoImpl;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Author : SachinSilva
 */
public class UserRepositoryImpl extends CrudRepoImpl<User, Integer> implements UserRepository {
    public UserRepositoryImpl() {
        super(User.class, "user", "user_id");
    }



    @Override
    public User findByUsername(String username, Connection connection) throws SQLException, NoSuchMethodException {
        // Get the SQL query from the annotation
        Method method = UserRepository.class.getMethod("findByUsername", String.class, Connection.class);
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
                    if (i == 0) {
                        paramMap.put(paramName, username);  // username is the first argument
                    } else if (i == 1) {
                        paramMap.put(paramName, connection);  // connection is the second argument
                    }
                }
            }

            // Convert named parameters (e.g., :username) to ?
            sql = convertNamedParametersToQuestionMarks(sql, paramMap);

            // Execute the query using CrudRepoImpl's executeQuery method
            List<User> users = executeQuery(sql, connection, paramMap.values().toArray());
            return (users.isEmpty()) ? null : users.get(0);
        }
        return null;
    }


    /**
     * Helper method to replace named parameters (e.g., :username) with ?
     */
    private String convertNamedParametersToQuestionMarks(String query, Map<String, Object> paramMap) {
        // Replace named parameters like :username with ?
        for (String paramName : paramMap.keySet()) {
            query = query.replace(":" + paramName, "?");
        }
        return query;
    }

    @Override
    public void updatePassword(Integer userId, String newPassword, Connection connection) throws SQLException, NoSuchMethodException {
        // Get the SQL query from the annotation
        Method method = UserRepository.class.getMethod("updatePassword", Integer.class, String.class, Connection.class);
        Query queryAnnotation = method.getAnnotation(Query.class);

        if (queryAnnotation != null) {
            String sql = queryAnnotation.value(); // Extract SQL from annotation
            // Execute the update using CrudRepoImpl's executeUpdate method
            executeUpdate(sql, connection, newPassword, userId);
        }
    }
}

