package com.zulykdev.starter.dto.request;

import com.zulykdev.starter.util.validation.ValidEmail;
import com.zulykdev.starter.util.validation.ValidPassword;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserRequest {

    public UserRequest(String name, String email, String password, List<PhoneRequest> phones) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phones = phones;
    }

    @NotBlank
    private String name;

    @ValidEmail
    private String email;

    @ValidPassword
    String password;

    @NotNull
    private List<PhoneRequest> phones;
}
