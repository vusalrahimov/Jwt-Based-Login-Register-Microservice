package io.desofme.cityservice.api;

import io.desofme.cityservice.dto.response.ResponseStatus;
import io.desofme.cityservice.dto.response.SingleResponseStatus;
import io.desofme.cityservice.exception.CustomException;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(CustomException.class)
    public SingleResponseStatus<ResponseStatus> handleCustomException(CustomException ex){
        return SingleResponseStatus.<ResponseStatus>builder()
                .status(new ResponseStatus(ex.getMessage(), ex.getCode()))
                .build();
    }

}
