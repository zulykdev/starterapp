package com.zulykdev.starter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zulykdev.starter.config.SecurityConfig;
import com.zulykdev.starter.dto.request.PhoneRequest;
import com.zulykdev.starter.dto.request.UserRequest;
import com.zulykdev.starter.dto.response.UserResponse;
import com.zulykdev.starter.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@Import(SecurityConfig.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("POST /api/v1/customer-onboarding/customers/initiation - Should return 201 with UserResponse")
    void register_ShouldReturnCreatedUserResponse() throws Exception {
        // Arrange
        UserRequest request = new UserRequest(
                "Hans",
                "hans@demo.com",
                "Hunter1234",
                List.of(new PhoneRequest("987562", "1", "51"))
        );
        UserResponse response = new UserResponse();
        response.setIsactive(true);

        Mockito.when(userService.registerUser(any(UserRequest.class))).thenReturn(response);

        // Act + Assert
        mockMvc.perform(post("/api/v1/customer-onboarding/customers/initiation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.isactive").value(true));

    }
}