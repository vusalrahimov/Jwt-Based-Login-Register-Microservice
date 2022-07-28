package io.desofme.userservice.dto.response;

import io.desofme.userservice.status.StatusCode;
import io.desofme.userservice.status.StatusMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseStatus {
    private String message;
    private int code;

    public static ResponseStatus getSuccess(){
        return new ResponseStatus(StatusMessage.SUCCESS, StatusCode.SUCCESS);
    }

}
