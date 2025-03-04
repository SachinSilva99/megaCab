package com.sachin.repository.repo.impl;

import jakarta.enterprise.context.ApplicationScoped;
import com.sachin.entity.User;
import com.sachin.repository.repo.UserRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Author : SachinSilva
 */
@ApplicationScoped
public class UserRepositoryImpl extends CrudRepoImpl implements UserRepository {



    public List<User> findAll(Connection connection) throws SQLException {
        List<User> users = executeQueryList("SELECT * FROM user", connection, User.class);
        return users;
    }

    @Override
    public Optional<User> findByUsername(Connection connection, String username) throws SQLException {
        User user = executeQuery(
                "SELECT * FROM user u WHERE u.username = ? ",
                connection,
                User.class,
                username
        );
        if (user == null) {
            return Optional.empty();
        }
        return Optional.of(user);
    }
}

