package org.example.demo1.repository.repo.impl;

import jakarta.enterprise.context.ApplicationScoped;
import org.example.demo1.entity.User;
import org.example.demo1.repository.repo.UserRepository;
import org.example.demo1.repository.crud.CrudRepoImpl;

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

