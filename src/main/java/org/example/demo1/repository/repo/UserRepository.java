package org.example.demo1.repository.repo;

import org.example.demo1.repository.SuperRepository;
import org.example.demo1.repository.entity.User;
import org.example.demo1.repository.repo.crud.CrudRepo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public interface UserRepository extends SuperRepository, CrudRepo{
     List<User> findAll(Connection connection) throws SQLException;
}
