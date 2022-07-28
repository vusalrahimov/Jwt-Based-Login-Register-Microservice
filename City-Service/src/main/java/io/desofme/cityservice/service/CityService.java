package io.desofme.cityservice.service;

import io.desofme.cityservice.dto.request.CityRequest;
import io.desofme.cityservice.dto.response.CityResponse;
import io.desofme.cityservice.dto.response.ResponseModel;

import java.util.List;

public interface CityService {
    ResponseModel<CityResponse> save(CityRequest cityRequest);

    ResponseModel<List<CityResponse>> getCities();

    ResponseModel<CityResponse> getCity(Long id);
}
