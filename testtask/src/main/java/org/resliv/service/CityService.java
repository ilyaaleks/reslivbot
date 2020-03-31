package org.resliv.service;

import org.resliv.dto.CityDto;
import org.resliv.dto.CityPageDto;
import org.resliv.mappers.CityMapper;
import org.resliv.model.City;
import org.resliv.repo.CityRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CityService {
    private CityRepo cityRepo;
    private CityMapper cityMapper;

    public CityService(CityRepo cityRepo) {
        this.cityRepo = cityRepo;
        this.cityMapper = CityMapper.INSTANCE;
    }

    public CityPageDto getCityList(Pageable pageable) {
        Page<City> cityPage = cityRepo.findAll(pageable);
        List<CityDto> cityDtos = cityMapper.cityListToCityDtoList(cityPage.getContent());
        return new CityPageDto(cityDtos,
                pageable.getPageNumber(),
                cityPage.getTotalPages());
    }

    public City addCity(City city) {
        return cityRepo.save(city);
    }

    public City updateCity(City city) {
        return cityRepo.save(city);
    }

    public City findCity(String cityName) {
        return cityRepo.findByName(cityName);
    }

    @Transactional
    public long deleteCity(int cityId) {
        return cityRepo.deleteById(cityId);
    }

    public void deleteCityById(long id) {
        cityRepo.deleteById(id);
    }
}
