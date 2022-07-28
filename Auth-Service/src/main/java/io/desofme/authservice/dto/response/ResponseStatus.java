package io.desofme.authservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseStatus {

    private String message;
    private int code;

    public static ResponseStatus getSuccess(){
        return new ResponseStatus("Success", 100);
    }

}
