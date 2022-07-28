package io.desofme.cityservice.api;

import io.desofme.cityservice.dto.request.CityRequest;
import io.desofme.cityservice.dto.response.CityResponse;
import io.desofme.cityservice.dto.response.ResponseModel;
import io.desofme.cityservice.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/city")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @PostMapping
    public ResponseModel<CityResponse> save(@RequestBody CityRequest cityRequest){
        return cityService.save(cityRequest);
    }

    @GetMapping
    public ResponseModel<List<CityResponse>> getCities(){
        return cityService.getCities();
    }

    @GetMapping("/{id}")
    public ResponseModel<CityResponse> getCity(@PathVariable("id") Long id){
        return cityService.getCity(id);
    }
}
