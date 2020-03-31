import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {CityModel} from '../model/city-model';
import {Observable, ReplaySubject, Subject} from 'rxjs';
import {CityModelDto} from '../model/city-model-dto';

@Injectable({
  providedIn: 'root'
})
export class CityService {
  private _savedCity: Subject<CityModel>=new ReplaySubject(1);
  private _updatedCity: Subject<CityModel>=new ReplaySubject(1);

  constructor(private httpClient: HttpClient) {
  }

  public saveCity(city: CityModel): Observable<CityModel> {
    return this.httpClient.post<CityModel>('/api/cities', city);
  }

  public getCities(page: number): Observable<CityModelDto> {
    return this.httpClient.get<CityModelDto>('/api/cities'+'?page=' + page + '&size=5&sort=id,DESC');
  }

  public updateCity(city: CityModel): Observable<CityModel> {
    return this.httpClient.put<CityModel>('/api/cities', city);
  }

  public deleteCity(cityId: number): void {
    this.httpClient.delete<void>('/api/cities?cityId=' + cityId).subscribe();
  }


  get savedCity(): Subject<CityModel> {
    return this._savedCity;
  }

  set savedCity(value: Subject<CityModel>) {
    this._savedCity = value;
  }

  get updatedCity(): Subject<CityModel> {
    return this._updatedCity;
  }

  set updatedCity(value: Subject<CityModel>) {
    this._updatedCity = value;
  }
}
