package org.resliv.controller;

import org.resliv.dto.CityDto;
import org.resliv.dto.CityPageDto;
import org.resliv.mappers.CityMapper;
import org.resliv.model.City;
import org.resliv.service.CityService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200/", allowedHeaders = "*")
@RestController
@RequestMapping("/api/cities")
public class CityController {
    private CityService cityService;
    private CityMapper cityMapper;

    public CityController(CityService cityService) {
        this.cityService = cityService;
        this.cityMapper = CityMapper.INSTANCE;
    }

    @GetMapping
    private ResponseEntity<CityPageDto> getCityList(Pageable pageable) {
        CityPageDto cityPageDto=cityService.getCityList(pageable);
        if(cityPageDto==null)
        {
            return ResponseEntity.badRequest().build();
        }
        else
        {
            return ResponseEntity.ok(cityPageDto);
        }
    }

    @PostMapping
    private ResponseEntity<CityDto> saveCity(@RequestBody CityDto cityDto) {
        City city = cityMapper.cityDtoToCity(cityDto);
        City saveCity = cityService.addCity(city);
        if (saveCity != null) {
            return ResponseEntity.ok(cityMapper.cityToCityDto(saveCity));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping
    private ResponseEntity<CityDto> updateCity(@RequestBody CityDto cityDto) {
        City city = cityMapper.cityDtoToCity(cityDto);
        city = cityService.updateCity(city);
        return ResponseEntity.ok(cityMapper.cityToCityDto(city));
    }

    @DeleteMapping
    private ResponseEntity deleteCity(@RequestParam int cityId) {
        long deletedId = cityService.deleteCity(cityId);
        if (deletedId == cityId) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
