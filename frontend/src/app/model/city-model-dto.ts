import {CityModel} from './city-model';

export interface CityModelDto {
  cities: CityModel[];
  currentPage: number;
  totalPage: number;
}
