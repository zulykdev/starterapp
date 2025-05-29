package com.zulykdev.starter.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneRequest {

    public PhoneRequest(String number, String citycode, String contrycode) {
        this.number = number;
        this.citycode = citycode;
        this.contrycode = contrycode;
    }

    @NotBlank
    private String number;

    @NotBlank
    private String citycode;

    @NotBlank
    private String contrycode;
}
