package io.desofme.cityservice.service.impl;

import io.desofme.cityservice.dto.request.CityRequest;
import io.desofme.cityservice.dto.response.CityResponse;
import io.desofme.cityservice.dto.response.ResponseModel;
import io.desofme.cityservice.dto.response.ResponseStatus;
import io.desofme.cityservice.entity.City;
import io.desofme.cityservice.exception.CustomException;
import io.desofme.cityservice.repo.CityRepo;
import io.desofme.cityservice.service.CityService;
import io.desofme.cityservice.status.StatusCode;
import io.desofme.cityservice.status.StatusMessage;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepo cityRepo;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public ResponseModel<CityResponse> save(CityRequest cityRequest) {
        City city = modelMapper.map(cityRequest, City.class);
        city.setCreatedAt(new Date());
        City savedCity = cityRepo.save(city);
        CityResponse cityResponse = modelMapper.map(savedCity, CityResponse.class);
        return ResponseModel.<CityResponse>builder()
                .response(cityResponse)
                .status(ResponseStatus.getSuccess())
                .build();
    }

    @Override
    public ResponseModel<List<CityResponse>> getCities() {
        List<City> cityList = cityRepo.findAll();
        List<CityResponse> cityResponses = cityList.stream()
                .map(c -> modelMapper.map(c, CityResponse.class))
                .collect(Collectors.toList());
        return ResponseModel.<List<CityResponse>>builder()
                .response(cityResponses)
                .status(ResponseStatus.getSuccess())
                .build();
    }

    @Override
    public ResponseModel<CityResponse> getCity(Long id) {
        City city = cityRepo.findById(id)
                .orElseThrow(() -> new CustomException(StatusMessage.CITY_NOT_FOUND, StatusCode.CITY_NOT_FOUND));
        CityResponse cityResponse = modelMapper.map(city, CityResponse.class);
        return ResponseModel.<CityResponse>builder()
                .response(cityResponse)
                .status(ResponseStatus.getSuccess())
                .build();
    }

}
