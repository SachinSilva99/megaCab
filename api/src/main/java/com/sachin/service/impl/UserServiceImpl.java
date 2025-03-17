package com.sachin.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import com.sachin.dto.other.HeaderHolder;
import com.sachin.dto.request.UserLoginRequestDTO;
import com.sachin.dto.request.UserRequestDTO;
import com.sachin.dto.response.LoginResponseDTO;
import com.sachin.exception.AppException;
import com.sachin.entity.Customer;
import com.sachin.entity.User;
import com.sachin.repository.repo.CustomerRepository;
import com.sachin.repository.transaction.TransactionManager;
import com.sachin.repository.repo.UserRepository;
import com.sachin.repository.db.DBConnection;
import com.sachin.security.JwtUtil;
import com.sachin.service.UserService;
import com.sachin.util.Mapper;
import com.sachin.util.ResponseDTO;
import lombok.Data;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import static com.sachin.util.PasswordUtil.checkPassword;
import static com.sachin.util.PasswordUtil.hashPassword;

@ApplicationScoped
@Data
public class UserServiceImpl implements UserService {

    @Inject
    private UserRepository userRepository;
    @Inject
    private CustomerRepository customerRepository;
    @Inject
    private HeaderHolder headerHolder;


    @Override
    public User getUserLoggedInUser() {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String username = JwtUtil.getClaims(headerHolder.getToken()).getSubject();
            User user = userRepository.findByUsername(connection, username).orElseThrow();
            connection.close();
            return user;
        } catch (Exception e) {
            throw new AppException("User Not found!");
        }
    }

    @Override
    public ResponseDTO<Object> register(UserRequestDTO userRequestDTO) {

        TransactionManager.executeTransaction(connection -> {
            User user = Mapper.toUser(userRequestDTO);
            String hashedPassword = hashPassword(userRequestDTO.getPassword());
            user.setPassword(hashedPassword);
            User savedUser;
            try {
                userRepository.findByUsername(connection, userRequestDTO.getUsername()).ifPresent((user1) -> {
                    throw new AppException("User already exists!");
                });
                customerRepository.findByNic(connection, userRequestDTO.getNic()).ifPresent((customer1) -> {
                    throw new AppException("Customer already exists!");
                });
                Customer customer = Mapper.toCustomer(userRequestDTO);
                user.setRole("USER");
                savedUser = userRepository.save(connection, User.class, "user", "id", user);
                customer.setUserId(savedUser.getId());

                customer.setRegistrationNumber(UUID.randomUUID().toString());
                return customerRepository.save(connection, Customer.class, "customer", "id", customer);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return ResponseDTO.success();
    }


    @Override
    public ResponseDTO<LoginResponseDTO> login(UserLoginRequestDTO requestDTO) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();

            String username = requestDTO.getUsername();
            String password = requestDTO.getPassword();

            User user = userRepository.findByUsername(connection, username)
                    .orElseThrow(() -> new AppException("User not found"));

            boolean checkPassword = checkPassword(password, user.getPassword());
            if (checkPassword) {
                String token = JwtUtil.generateToken(username, "USER");
                LoginResponseDTO loginResponseDTO = LoginResponseDTO.builder()
                        .accessToken(token)
                        .expTime(String.valueOf(JwtUtil.EXPIRATION_TIME))
                        .build();
                return ResponseDTO.success(loginResponseDTO);
            }
            throw new AppException("Incorrect password!!");

        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(e.getMessage());
        }
    }

}
