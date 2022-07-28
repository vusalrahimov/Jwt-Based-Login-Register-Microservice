package io.desofme.userservice.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
    private String name;
    private String surname;
    private String username;
    private Long cityId;
}
