package com.zulykdev.starter.service;

import static org.junit.jupiter.api.Assertions.*;

import com.zulykdev.starter.dto.request.PhoneRequest;
import com.zulykdev.starter.dto.request.UserRequest;
import com.zulykdev.starter.dto.response.UserResponse;
import com.zulykdev.starter.util.exception.EmailAlreadyExistsException;
import com.zulykdev.starter.model.Phone;
import com.zulykdev.starter.model.User;
import com.zulykdev.starter.repository.UserRepository;
import com.zulykdev.starter.security.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static com.zulykdev.starter.util.Constants.EMAIL_ALREADY_EXISTS_MESSAGE;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private UserRepository userRepository;
    private JwtUtil jwtUtil;
    private UserService userService;

    @BeforeEach
    void setup() {
        userRepository = mock(UserRepository.class);
        jwtUtil = mock(JwtUtil.class);
        userService = new UserService(userRepository, jwtUtil);
    }

    @Test
    @DisplayName("registerUser - should save user and return UserResponse")
    void registerUser_ShouldSaveUserAndReturnResponse() {
        // Arrange
        UserRequest request = new UserRequest(
                "Hans",
                "hans@demo.com",
                "Password1234",
                List.of(new PhoneRequest("123456", "1", "51"))
        );

        when(userRepository.existsByEmail("hans@demo.com")).thenReturn(false);
        when(jwtUtil.generateToken("hans@demo.com")).thenReturn("mocked-token");

        // Creamos el usuario simulado que será "guardado"
        User userToSave = new User();
        userToSave.setId(UUID.randomUUID());
        userToSave.setName(request.getName());
        userToSave.setEmail(request.getEmail());
        userToSave.setPassword(request.getPassword());
        userToSave.setToken("mocked-token");
        userToSave.setActive(true);
        userToSave.setLastLogin(LocalDateTime.now());
        userToSave.setPhones(List.of(new Phone()));

        // Simula guardar y devolver el usuario
        when(userRepository.save(any(User.class))).thenReturn(userToSave);

        // Act
        UserResponse response = userService.registerUser(request);

        // Assert
        assertNotNull(response);
        assertEquals("mocked-token", response.getToken());
        assertTrue(response.isIsactive());
    }

    @Test
    @DisplayName("registerUser - should throw exception if email exists")
    void registerUser_ShouldThrowIfEmailExists() {
        // Arrange
        UserRequest request = new UserRequest(
                "hans",
                "hans@demo.com",
                "Password1234",
                List.of()
        );

        when(userRepository.existsByEmail("hans@demo.com")).thenReturn(true);

        // Act + Assert
        EmailAlreadyExistsException exception = assertThrows(
                EmailAlreadyExistsException.class,
                () -> userService.registerUser(request)
        );

        assertEquals(EMAIL_ALREADY_EXISTS_MESSAGE, exception.getMessage());
    }
}