package com.bren.carshop.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter
@Getter
public class UserRequest {
    @NotBlank
    private String username;
    @Size(min = 3, max = 30)
    private String password;


}
