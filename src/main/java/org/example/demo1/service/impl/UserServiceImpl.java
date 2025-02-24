package org.example.demo1.service.impl;

import org.example.demo1.dto.request.UserLoginRequestDTO;
import org.example.demo1.dto.request.UserRequestDTO;
import org.example.demo1.dto.response.LoginResponseDTO;
import org.example.demo1.exception.AppException;
import org.example.demo1.repository.RepositoryFactory;
import org.example.demo1.repository.RepositoryType;
import org.example.demo1.repository.entity.Customer;
import org.example.demo1.repository.entity.User;
import org.example.demo1.repository.repo.CustomerRepository;
import org.example.demo1.repository.repo.transaction.TransactionManager;
import org.example.demo1.repository.repo.UserRepository;
import org.example.demo1.repository.repo.db.DBConnection;
import org.example.demo1.security.JwtUtil;
import org.example.demo1.service.UserService;
import org.example.demo1.util.Mapper;
import org.example.demo1.util.ResponseDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.example.demo1.util.PasswordUtil.checkPassword;
import static org.example.demo1.util.PasswordUtil.hashPassword;

public class UserServiceImpl implements UserService {


    private final UserRepository userRepository = RepositoryFactory.getInstance().getRepo(RepositoryType.USER);
    private final CustomerRepository customerRepository = RepositoryFactory.getInstance().getRepo(RepositoryType.CUSTOMER);

    @Override
    public List<User> getAllUsers() {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            return userRepository.findAll(connection);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(e.getMessage());
        }
    }

    @Override
    public User getUser() throws SQLException, NoSuchMethodException {
        Connection connection = DBConnection.getInstance().getConnection();
//        User user = userRepository.findByUsername("sachin2", connection);

        /* boolean updated = userRepository.executeQuery("UPDATE user u SET u.username = ? WHERE u.username = ?"
                , connection, Boolean.class, "sachin","sachin2");*/

        List<User> users = userRepository.executeQueryList("SELECT * FROM user", connection, User.class);
//        throw new AppException("User not found");
        System.out.println(users);
        return null;
    }

    @Override
    public ResponseDTO<Object> register(UserRequestDTO userRequestDTO) {

        TransactionManager.executeTransaction(connection -> {
            User user = Mapper.toUser(userRequestDTO);
            String hashedPassword = hashPassword(userRequestDTO.getPassword());
            user.setPassword(hashedPassword);
            User savedUser;
            try {
                Customer customer = Mapper.toCustomer(userRequestDTO);

                savedUser = userRepository.executeSave(connection, User.class, "users", "id", user);
                customer.setUserId(savedUser.getId());
                customerRepository.executeSave(connection, Customer.class, "customers", "id", customer);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        });
        return ResponseDTO.success();
    }


    @Override
    public ResponseDTO<LoginResponseDTO> login(UserLoginRequestDTO requestDTO) throws SQLException {
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
            throw new AppException("Something went wrong!!");
        }
    }
}
