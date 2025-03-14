package com.sachin;

import com.sachin.dto.request.DriverRequestDTO;
import com.sachin.dto.response.DriverResponseDTO;
import com.sachin.repository.repo.DriverRepository;
import com.sachin.repository.repo.impl.DriverRepositoryImpl;
import com.sachin.service.impl.DriverServiceImpl;
import com.sachin.util.ResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class DriverServiceImplTest {


    @InjectMocks
    private DriverServiceImpl driverService;


    private final DriverRepository driverRepository = new DriverRepositoryImpl();


    @BeforeEach
    void setUp() {
        driverService.driverRepository = driverRepository;
    }

    @Test
    void testRegister_Success() {
        DriverRequestDTO saman = DriverRequestDTO.builder()
                .name("Saman")
                .licenseNumber("DL00189")
                .phone("0772398293").build();

        ResponseDTO<DriverResponseDTO> driver = driverService.createDriver(saman);
        String status = driver.status();
        // Assert
        assertEquals("00", status);
    }

    @Test
    void testGetAll_Success() {

        ResponseDTO
                <List<DriverResponseDTO>> driver = driverService.getAllDrivers();
        String status = driver.status();
        // Assert
        assertEquals("00", status);
    }
}
