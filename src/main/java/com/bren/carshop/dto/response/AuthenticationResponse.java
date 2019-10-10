package com.bren.carshop.dto.response;

import com.bren.carshop.entity.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationResponse {
    private String username;
    private String token;
    private UserRole userRole;

    public AuthenticationResponse(String username, String token, UserRole userRole) {
        this.username = username;
        this.token = token;
        this.userRole = userRole;
    }
}

