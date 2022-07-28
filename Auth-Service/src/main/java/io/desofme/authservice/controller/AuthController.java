package io.desofme.authservice.controller;

import io.desofme.authservice.dto.request.AppUserRequest;
import io.desofme.authservice.dto.request.LoginRequest;
import io.desofme.authservice.dto.response.AppUserResponse;
import io.desofme.authservice.dto.response.LoginResponse;
import io.desofme.authservice.dto.response.ResponseModel;
import io.desofme.authservice.entity.AppUser;
import io.desofme.authservice.service.AppUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AppUserService appUserService;

    @PostMapping("/login")
    public ResponseModel<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        return appUserService.login(loginRequest);
    }

    @PostMapping("/register")
    public ResponseModel<AppUserResponse> register(@RequestBody AppUserRequest appUserRequest){
        return appUserService.register(appUserRequest);
    }

}
