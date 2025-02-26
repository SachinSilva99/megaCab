package org.example.demo1.util;

import org.example.demo1.dto.request.UserRequestDTO;
import org.example.demo1.dto.response.DistanceResponseDTO;
import org.example.demo1.dto.response.DriverResponseDTO;
import org.example.demo1.entity.Customer;
import org.example.demo1.entity.Distance;
import org.example.demo1.entity.Driver;
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
    public static DriverResponseDTO toDriverResponseDTO(Driver driver) {
        return modelMapper.map(driver, DriverResponseDTO.class);
    }
    public static DistanceResponseDTO toDistanceResponseDTO(Distance distance) {
        return modelMapper.map(distance, DistanceResponseDTO.class);
    }
}
