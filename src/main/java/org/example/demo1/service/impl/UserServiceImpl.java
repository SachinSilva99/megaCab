package org.example.demo1.service.impl;

import org.example.demo1.dto.request.UserLoginRequestDTO;
import org.example.demo1.dto.request.UserRequestDTO;
import org.example.demo1.exception.AppException;
import org.example.demo1.repository.RepositoryFactory;
import org.example.demo1.repository.RepositoryType;
import org.example.demo1.repository.entity.User;
import org.example.demo1.repository.repo.UserRepository;
import org.example.demo1.repository.repo.db.DBConnection;
import org.example.demo1.service.UserService;
import org.example.demo1.util.Mapper;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {


    private final UserRepository userRepository = RepositoryFactory.getInstance().getRepo(RepositoryType.USER);

    @Override
    public List<User> getAllUsers() {
        try {
            return userRepository.findAll(DBConnection.getInstance().getConnection());
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(e.getMessage());
        }
    }

    @Override
    public User getUser() throws SQLException, NoSuchMethodException {
        Connection connection = DBConnection.getInstance().getConnection();
        User user = userRepository.findByUsername("sachin2", connection);
//        throw new AppException("User not found");
        return user;
    }

    @Override
    public void register(UserRequestDTO userRequestDTO) {
        User user = Mapper.toUser(userRequestDTO);
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            userRepository.save(user, connection);
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void login(UserLoginRequestDTO requestDTO) throws SQLException {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            User user = userRepository.findByUsername(requestDTO.getUsername(), connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
