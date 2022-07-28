package io.desofme.authservice.service;

import io.desofme.authservice.dto.request.AppUserRequest;
import io.desofme.authservice.dto.request.LoginRequest;
import io.desofme.authservice.dto.response.AppUserResponse;
import io.desofme.authservice.dto.response.LoginResponse;
import io.desofme.authservice.dto.response.ResponseModel;
import io.desofme.authservice.dto.response.ResponseStatus;
import io.desofme.authservice.entity.AppUser;
import io.desofme.authservice.jwt.JwtManager;
import io.desofme.authservice.repo.AppUserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppUserService {

    private final AppUserRepo appUserRepo;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final AuthenticationManager auth;
    private final JwtManager jwtManager;

    public ResponseModel<LoginResponse> login(LoginRequest loginRequest) {
        ResponseModel<LoginResponse> loginResponseResponseModel;
        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
            Authentication authentication = auth.authenticate(authenticationToken);
            AppUser appUser = (AppUser) authentication.getPrincipal();
            String token = jwtManager.generateToken(appUser.getId());
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setToken(token);
            loginResponse.setUserId(appUser.getId());
            loginResponseResponseModel = ResponseModel.<LoginResponse>builder()
                    .response(loginResponse)
                    .status(ResponseStatus.getSuccess())
                    .build();

        }catch (Exception ex){
            ex.printStackTrace();
            loginResponseResponseModel = ResponseModel.<LoginResponse>builder()
                    .response(new LoginResponse())
                    .status(new ResponseStatus("Unauthorized",104))
                    .build();
        }

        return loginResponseResponseModel;
    }

    public ResponseModel<AppUserResponse> register(AppUserRequest appUserRequest) {
        AppUser appUser = modelMapper.map(appUserRequest, AppUser.class);
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        AppUser savedUser = appUserRepo.save(appUser);
        return ResponseModel.<AppUserResponse>builder()
                .status(ResponseStatus.getSuccess())
                .response(modelMapper.map(savedUser, AppUserResponse.class))
                .build();
    }
}
