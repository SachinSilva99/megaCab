package com.sachin.util;

import com.sachin.dto.request.CarRequestDTO;
import com.sachin.dto.request.DriverRequestDTO;
import com.sachin.dto.request.UserRequestDTO;
import com.sachin.dto.response.BookingDetailsResponseDTO;
import com.sachin.dto.response.CarResponseDTO;
import com.sachin.dto.response.DistanceResponseDTO;
import com.sachin.dto.response.DriverResponseDTO;
import com.sachin.entity.*;
import org.example.demo1.entity.*;
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

    public static BookingDetailsResponseDTO bookingDetailsResponseDTO(Booking booking) {
        return modelMapper.map(booking, BookingDetailsResponseDTO.class);
    }

    public static Car toCar(CarRequestDTO carRequestDTO) {
        return modelMapper.map(carRequestDTO, Car.class);
    }

    public static CarResponseDTO toCarResponseDTO(Car car) {
        return modelMapper.map(car, CarResponseDTO.class);
    }

    public static Driver toDriver(DriverRequestDTO driverRequestDTO) {
        return modelMapper.map(driverRequestDTO, Driver.class);
    }
}
