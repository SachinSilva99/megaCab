package com.sachin;

import com.sachin.dto.other.HeaderHolder;
import com.sachin.dto.request.UserLoginRequestDTO;
import com.sachin.dto.request.UserRequestDTO;
import com.sachin.dto.response.LoginResponseDTO;
import com.sachin.repository.repo.CustomerRepository;
import com.sachin.repository.repo.UserRepository;
import com.sachin.repository.repo.impl.CustomerRepoImpl;
import com.sachin.repository.repo.impl.UserRepositoryImpl;
import com.sachin.security.JwtUtil;
import com.sachin.service.impl.UserServiceImpl;
import com.sachin.util.ResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {


    @InjectMocks
    private  UserServiceImpl userService;

    private final UserRepository userRepository = new UserRepositoryImpl();

    private final CustomerRepository customerRepository = new CustomerRepoImpl();

    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        userService.userRepository = userRepository;
        userService.customerRepository = customerRepository;
        userService.headerHolder = new HeaderHolder();
    }
    @Test
    void testRegister_Success() {
        // Arrange
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setUsername("Silva");
        userRequestDTO.setName("Silva");
        userRequestDTO.setPhone("0772739121");
        userRequestDTO.setPassword("password123");
        userRequestDTO.setNic("199919603210");
        // Act
        ResponseDTO<Object> response = userService.register(userRequestDTO);
        // Assert
        assertEquals("00", response.status());
    }
    @Test
    void testLogin_Success() {

        // Step 2: Create login request with valid credentials
        UserLoginRequestDTO loginRequest = new UserLoginRequestDTO();
        loginRequest.setUsername("dilshan");
        loginRequest.setPassword("123456");

        // Step 3: Perform login
        ResponseDTO<LoginResponseDTO> response = userService.login(loginRequest);

        // Step 4: Verify the response
        assertEquals("00", response.status());
        assertNotNull(response.content());
        assertNotNull(response.content().getAccessToken());
        assertNotNull(response.content().getExpTime());

        // Optional: Validate the generated token
        String token = response.content().getAccessToken();
        assertNotNull(JwtUtil.validateToken(token)); // Assuming you have a method to validate tokens
    }
}
