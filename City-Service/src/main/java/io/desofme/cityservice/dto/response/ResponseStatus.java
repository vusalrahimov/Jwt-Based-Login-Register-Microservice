package io.desofme.cityservice.dto.response;

import io.desofme.cityservice.status.StatusCode;
import io.desofme.cityservice.status.StatusMessage;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseStatus {
    private String message;
    private int code;


    public static ResponseStatus getSuccess(){
        return new ResponseStatus(StatusMessage.SUCCESS, StatusCode.SUCCESS);
    }
}
