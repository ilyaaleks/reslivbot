package org.resliv.mappers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.resliv.dto.CityDto;
import org.resliv.model.City;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-03-31T23:17:00+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_221 (Oracle Corporation)"
)
public class CityMapperImpl implements CityMapper {

    @Override
    public CityDto cityToCityDto(City city) {
        if ( city == null ) {
            return null;
        }

        CityDto cityDto = new CityDto();

        cityDto.setId( city.getId() );
        cityDto.setName( city.getName() );
        cityDto.setDescription( city.getDescription() );

        return cityDto;
    }

    @Override
    public List<CityDto> cityListToCityDtoList(List<City> cities) {
        if ( cities == null ) {
            return null;
        }

        List<CityDto> list = new ArrayList<CityDto>( cities.size() );
        for ( City city : cities ) {
            list.add( cityToCityDto( city ) );
        }

        return list;
    }

    @Override
    public City cityDtoToCity(CityDto cityDto) {
        if ( cityDto == null ) {
            return null;
        }

        City city = new City();

        city.setId( cityDto.getId() );
        city.setName( cityDto.getName() );
        city.setDescription( cityDto.getDescription() );

        return city;
    }
}
