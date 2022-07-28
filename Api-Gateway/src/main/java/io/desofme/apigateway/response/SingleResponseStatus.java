package io.desofme.apigateway.response;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SingleResponseStatus implements Serializable
{
    ResponseStatus responseStatus;
}
