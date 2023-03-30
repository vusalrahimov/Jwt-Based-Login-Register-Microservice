package io.desofme.userservice.api;

import io.desofme.userservice.dto.request.UserRequest;
import io.desofme.userservice.dto.response.ResponseModel;
import io.desofme.userservice.dto.response.UserResponse;
import io.desofme.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
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
    public ResponseModel<List<UserResponse>> getUsers(HttpServletRequest request){
        log.info("Request sended by userId: {}", request.getHeader("userId"));
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public ResponseModel<UserResponse> getUser(@PathVariable("id") Long id){
        return userService.getUser(id);
    }


}
