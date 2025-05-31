package com.zulykdev.starter.controller;

import com.zulykdev.starter.dto.request.UserRequest;
import com.zulykdev.starter.dto.response.UserResponse;
import com.zulykdev.starter.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Customer Onboarding", description = "Operaciones relacionadas con el proceso de incorporación de clientes según el estándar BIAN.")
@RestController
@RequestMapping("api/v1/customer-onboarding/customers/initiation")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(
            summary = "Iniciar incorporación de cliente",
            description = "Realiza la operación de 'initiate' para el proceso de incorporación de clientes según el estándar BIAN."
    )
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> register(@Valid @RequestBody UserRequest request) {
        UserResponse response = userService.registerUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
