package io.desofme.apigateway.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseStatus implements Serializable {

    private String message;
    private int code;

    public static ResponseStatus getSuccess(){
        return new ResponseStatus("Success", 100);
    }

}
