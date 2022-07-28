package io.desofme.userservice.service.impl;
import io.desofme.userservice.dto.request.UserRequest;
import io.desofme.userservice.dto.response.CityResponse;
import io.desofme.userservice.dto.response.ResponseModel;
import io.desofme.userservice.dto.response.ResponseStatus;
import io.desofme.userservice.dto.response.UserResponse;
import io.desofme.userservice.entity.User;
import io.desofme.userservice.exception.CustomException;
import io.desofme.userservice.feignclients.CityFeignClient;
import io.desofme.userservice.repo.UserRepo;
import io.desofme.userservice.service.UserService;
import io.desofme.userservice.status.StatusCode;
import io.desofme.userservice.status.StatusMessage;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final ModelMapper modelMapper;
    private final CityFeignClient cityFeignClient;

    @Override
    @Transactional
    public ResponseModel<UserResponse> save(UserRequest userRequest) {
        User user = modelMapper.map(userRequest, User.class);
        user.setCreatedAt(new Date());
        User savedUser = userRepo.save(user);
        UserResponse userResponse = modelMapper.map(savedUser, UserResponse.class);
        CityResponse cityResponse = cityFeignClient.getCity(savedUser.getCityId()).getResponse();
        userResponse.setCityResponse(cityResponse);
        return ResponseModel.<UserResponse>builder()
                .response(userResponse)
                .status(ResponseStatus.getSuccess())
                .build();
    }

    @Override
    public ResponseModel<List<UserResponse>> getUsers() {
        List<User> userList = userRepo.findAll();
        List<UserResponse> userResponses = userList.stream()
                .map(u->{
                   UserResponse userResponse =  modelMapper.map(u, UserResponse.class);
                   ResponseModel<CityResponse> cityResponseResponseModel = cityFeignClient.getCity(u.getCityId());
                   userResponse.setCityResponse(cityResponseResponseModel.getResponse());
                   return userResponse;
                }).collect(Collectors.toList());
        return ResponseModel.<List<UserResponse>>builder()
                .response(userResponses)
                .status(ResponseStatus.getSuccess())
                .build();
    }

    @Override
    public ResponseModel<UserResponse> getUser(Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(()->new CustomException(StatusMessage.USER_NOT_FOUND, StatusCode.USER_NOT_FOUND));
        UserResponse userResponse = modelMapper.map(user, UserResponse.class);
        userResponse.setCityResponse(cityFeignClient.getCity(user.getCityId()).getResponse());
        return ResponseModel.<UserResponse>builder()
                .response(userResponse)
                .status(ResponseStatus.getSuccess())
                .build();
    }
}
