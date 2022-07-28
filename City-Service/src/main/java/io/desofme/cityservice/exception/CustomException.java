package io.desofme.cityservice.exception;

import io.desofme.cityservice.dto.response.ResponseStatus;
import io.desofme.cityservice.status.StatusMessage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomException extends RuntimeException{

    private String message;
    private int code;

    public CustomException(String message, int code){
        super(message);
        this.message = message;
        this.code = code;
    }

}
