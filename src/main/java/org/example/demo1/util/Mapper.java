package org.example.demo1.util;

import org.example.demo1.dto.request.UserRequestDTO;
import org.example.demo1.entity.Customer;
import org.example.demo1.entity.User;
import org.modelmapper.ModelMapper;

/**
 * Author : SachinSilva
 */
public class Mapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static User toUser(UserRequestDTO userRequestDTO) {
        return modelMapper.map(userRequestDTO, User.class);
    }
    public static Customer toCustomer(UserRequestDTO userRequestDTO) {
        return modelMapper.map(userRequestDTO, Customer.class);
    }
}
