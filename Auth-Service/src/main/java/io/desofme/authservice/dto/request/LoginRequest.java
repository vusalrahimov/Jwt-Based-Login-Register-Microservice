package io.desofme.authservice.dto.request;


import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class LoginRequest {
    private String username;
    private String password;
}
