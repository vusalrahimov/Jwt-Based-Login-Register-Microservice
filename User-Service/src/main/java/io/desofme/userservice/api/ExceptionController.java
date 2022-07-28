package io.desofme.userservice.api;


import io.desofme.userservice.dto.response.ResponseStatus;
import io.desofme.userservice.dto.response.SingleResponseStatus;
import io.desofme.userservice.exception.CustomException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(CustomException.class)
    public SingleResponseStatus<ResponseStatus> handleCustomException(CustomException ex){
        return SingleResponseStatus.<ResponseStatus>builder()
                .status(new ResponseStatus(ex.getMessage(), ex.getCode()))
                .build();
    }

}
