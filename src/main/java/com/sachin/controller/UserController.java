package com.sachin.controller;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import com.sachin.annotations.RequestBody;
import com.sachin.annotations.RequestMapping;
import com.sachin.dto.other.HeaderHolder;
import com.sachin.dto.request.UserLoginRequestDTO;
import com.sachin.dto.request.UserRequestDTO;
import com.sachin.dto.response.LoginResponseDTO;
import com.sachin.entity.User;
import com.sachin.service.UserService;
import com.sachin.util.ResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;


@RequestMapping("/users")
@ApplicationScoped
public class UserController {
    @Inject
    private UserService userService;
    @Inject
    private HeaderHolder headerHolder;
    private static final Logger log = LoggerFactory.getLogger(UserController.class);


    @RequestMapping(value = "/login", method = RequestMapping.HttpMethod.POST)
    public ResponseDTO<LoginResponseDTO> login(@RequestBody UserLoginRequestDTO requestDTO) throws SQLException, NoSuchMethodException {
        return userService.login(requestDTO);
    }

    @RequestMapping(value = "/register", method = RequestMapping.HttpMethod.POST)
    public ResponseDTO<Object> register(@RequestBody UserRequestDTO userRequestDTO) {
        return userService.register(userRequestDTO);
    }


}