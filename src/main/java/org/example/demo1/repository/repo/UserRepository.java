package org.example.demo1.repository.repo;

import org.example.demo1.repository.SuperRepository;
import org.example.demo1.repository.annotations.Param;
import org.example.demo1.repository.annotations.Query;
import org.example.demo1.repository.entity.User;
import org.example.demo1.repository.repo.crud.CrudRepo;

import java.sql.Connection;
import java.sql.SQLException;


public interface UserRepository extends SuperRepository, CrudRepo<User, Integer> {
    @Query("SELECT * FROM user WHERE username = :username")
    User findByUsername(@Param("username") String username, Connection connection) throws SQLException, NoSuchMethodException;

    @Query("UPDATE user SET password = :password WHERE user_id = :userId")
    void updatePassword(@Param("userId") Integer userId, @Param("password") String newPassword, Connection connection) throws SQLException, NoSuchMethodException;
}
