package com.zulykdev.starter.dto.request;

import com.zulykdev.starter.util.validation.ValidEmail;
import com.zulykdev.starter.util.validation.ValidPassword;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    @NotBlank
    private String name;

    @ValidEmail
    private String email;

    @ValidPassword
    String password;

    @Valid
    @NotEmpty
    private List<PhoneRequest> phones;
}
