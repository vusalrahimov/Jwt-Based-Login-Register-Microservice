package io.desofme.userservice.service;

import io.desofme.userservice.dto.request.UserRequest;
import io.desofme.userservice.dto.response.ResponseModel;
import io.desofme.userservice.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    ResponseModel<UserResponse> save(UserRequest userRequest);

    ResponseModel<List<UserResponse>> getUsers();

    ResponseModel<UserResponse> getUser(Long id);
}
