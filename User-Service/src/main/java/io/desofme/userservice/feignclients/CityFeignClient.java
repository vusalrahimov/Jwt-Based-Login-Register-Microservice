package io.desofme.userservice.feignclients;

import io.desofme.userservice.dto.response.CityResponse;
import io.desofme.userservice.dto.response.ResponseModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(value = "CITY-SERVICE")
public interface CityFeignClient {

    @GetMapping("/api/city/{id}")
    ResponseModel<CityResponse> getCity(@PathVariable("id") Long id);
}
