package io.desofme.authservice.dto.response;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private Long userId;
}
