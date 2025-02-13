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
        Method method = UserRepository.class.getMethod("findByUsername", String.class, Connection.class);
        return findByQuery(method, connection, username, connection); // Use the generic query method
    }

    @Override
    public void updatePassword(Integer userId, String newPassword, Connection connection) throws SQLException, NoSuchMethodException {
        Method method = UserRepository.class.getMethod("updatePassword", Integer.class, String.class, Connection.class);
        findByQuery(method, connection, newPassword, userId, connection); // Use the generic query method
    }
}

