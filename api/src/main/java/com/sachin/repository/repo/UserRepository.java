package com.sachin.repository.repo;

import com.sachin.repository.SuperRepository;
import com.sachin.entity.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public interface UserRepository extends SuperRepository, CrudRepo{
     List<User> findAll(Connection connection) throws SQLException;
     Optional<User> findByUsername(Connection connection, String username) throws SQLException;
}
