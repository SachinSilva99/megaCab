package org.example.demo1.service;

import org.example.demo1.dto.request.UserLoginRequestDTO;
import org.example.demo1.dto.request.UserRequestDTO;
import org.example.demo1.dto.response.LoginResponseDTO;
import org.example.demo1.entity.User;
import org.example.demo1.util.ResponseDTO;

import java.sql.SQLException;
import java.util.List;

/**
 * Author : SachinSilva
 */
public interface UserService extends SuperService {
    List<User> getAllUsers();

    User getUser() throws SQLException, NoSuchMethodException;
    User getUserLoggedInUser();

    ResponseDTO<Object> register(UserRequestDTO userRequestDTO);

    ResponseDTO<LoginResponseDTO> login(UserLoginRequestDTO requestDTO) throws SQLException, NoSuchMethodException;
}
