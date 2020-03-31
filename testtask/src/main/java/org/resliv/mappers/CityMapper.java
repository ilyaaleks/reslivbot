package org.resliv.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.resliv.dto.CityDto;
import org.resliv.model.City;
import org.resliv.model.User;

import java.util.List;

@Mapper
public interface CityMapper {
    CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);

    CityDto cityToCityDto(City city);

    List<CityDto> cityListToCityDtoList(List<City> cities);

    City cityDtoToCity(CityDto cityDto);
}
