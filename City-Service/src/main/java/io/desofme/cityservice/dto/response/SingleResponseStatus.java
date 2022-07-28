package io.desofme.cityservice.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SingleResponseStatus<T> {
    private T status;
}
