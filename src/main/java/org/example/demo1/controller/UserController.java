package org.example.demo1.controller;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.example.demo1.GreetingService;
import org.example.demo1.annotations.RequestBody;
import org.example.demo1.annotations.RequestMapping;
import org.example.demo1.dto.other.HeaderHolder;
import org.example.demo1.dto.request.UserLoginRequestDTO;
import org.example.demo1.dto.request.UserRequestDTO;
import org.example.demo1.dto.response.LoginResponseDTO;
import org.example.demo1.entity.User;
import org.example.demo1.service.UserService;
import org.example.demo1.util.ResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;


@RequestMapping("/users")
@ApplicationScoped
public class UserController {
    @Inject
    private GreetingService greetingService;
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

    @RequestMapping(value = "/test", method = RequestMapping.HttpMethod.GET)
    public String getUsers() {
        return greetingService.getGreeting();
    }

    @RequestMapping(value = "/tests", method = RequestMapping.HttpMethod.GET)
    public List<User> getUser() throws SQLException, NoSuchMethodException {
        log.info(headerHolder.getToken());
        return userService.getAllUsers();
    }
}