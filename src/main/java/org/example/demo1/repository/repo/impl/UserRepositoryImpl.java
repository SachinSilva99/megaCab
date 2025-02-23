package org.example.demo1.repository.repo.impl;

import org.example.demo1.repository.entity.User;
import org.example.demo1.repository.repo.UserRepository;
import org.example.demo1.repository.repo.crud.CrudRepoImpl;
import org.example.demo1.repository.repo.db.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Author : SachinSilva
 */
public class UserRepositoryImpl extends CrudRepoImpl implements UserRepository {



    public List<User> findAll(Connection connection) throws SQLException {
        List<User> users = executeQueryList("SELECT * FROM users", connection, User.class);
        return users;
    }
}

