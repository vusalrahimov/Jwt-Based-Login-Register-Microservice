package io.desofme.userservice.api;

import io.desofme.userservice.dto.request.UserRequest;
import io.desofme.userservice.dto.response.ResponseModel;
import io.desofme.userservice.dto.response.UserResponse;
import io.desofme.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseModel<UserResponse> save(@RequestBody UserRequest userRequest){
        return userService.save(userRequest);
    }

    @GetMapping
    public ResponseModel<List<UserResponse>> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public ResponseModel<UserResponse> getUser(@PathVariable("id") Long id){
        return userService.getUser(id);
    }


}
