package com.zulykdev.starter.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PhoneRequest {

    @NotBlank
    private String number;

    @NotBlank
    private String citycode;

    @NotBlank
    private String contrycode;
}
