package org.example.demo1.service;

import org.example.demo1.dto.request.UserLoginRequestDTO;
import org.example.demo1.dto.request.UserRequestDTO;
import org.example.demo1.repository.entity.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Author : SachinSilva
 */
public interface UserService extends SuperService {
    List<User> getAllUsers();

    User getUser() throws SQLException, NoSuchMethodException;

    void register(UserRequestDTO userRequestDTO);

    void login(UserLoginRequestDTO requestDTO) throws SQLException, NoSuchMethodException;
}
