package com.sachin.service;

import com.sachin.dto.request.UserLoginRequestDTO;
import com.sachin.dto.request.UserRequestDTO;
import com.sachin.dto.response.LoginResponseDTO;
import com.sachin.entity.User;
import com.sachin.util.ResponseDTO;

import java.sql.SQLException;
import java.util.List;

/**
 * Author : SachinSilva
 */
public interface UserService extends SuperService {

    User getUserLoggedInUser();

    ResponseDTO<Object> register(UserRequestDTO userRequestDTO);

    ResponseDTO<LoginResponseDTO> login(UserLoginRequestDTO requestDTO);
}
