import {Component, OnInit} from '@angular/core';
import {CityService} from '../../services/city.service';
import {MatDialog, MatDialogConfig} from '@angular/material';
import {NotificationComponent} from '../notification/notification.component';
import {CityModel} from '../../model/city-model';
import {CityModelDto} from '../../model/city-model-dto';

@Component({
  selector: 'app-city',
  templateUrl: './city.component.html',
  styleUrls: ['./city.component.css']
})
export class CityComponent implements OnInit {
  public cities: CityModel[];
  itemsPerPage: number = 5;
  totalItems: any;
  page: any;
  previousPage: any;

  constructor(public dialog: MatDialog,
              public cityService: CityService) {
  }


  ngOnInit() {
    this.cityService.getCities(0).subscribe((citiesDto: CityModelDto) => {
      if (citiesDto != null) {
        this.totalItems = citiesDto.totalPage * this.itemsPerPage;
        this.page = citiesDto.currentPage + 1;
        this.cities = citiesDto.cities;
      }
    });
    this.cityService.savedCity.subscribe((city: CityModel) => {
      if (city != null) {
        this.cities.unshift(city);
        this.cityService.savedCity.next(null);
      }
    });
    this.cityService.updatedCity.subscribe((city: CityModel) => {
      if (city != null) {
        let objIndex: number = this.cities.findIndex((obj => obj.id == city.id));
        this.cities[objIndex] = city;
        this.cityService.savedCity.next(null);
      }
    });
  }

  loadPage(page: number) {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.cityService.getCities(page - 1).subscribe((cityPage: CityModelDto) => {
        this.totalItems = cityPage.totalPage * this.itemsPerPage;
        this.page = cityPage.currentPage + 1;
        this.cities = cityPage.cities;
      });
    }
  }

  deleteCity(cityId: number) {
    this.cityService.deleteCity(cityId);
    this.cities = this.cities.filter(i => i.id != cityId);
  }

  updateCity(cityId: number) {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.width = '50%';
    dialogConfig.data = {
      cityId: cityId,
    };
    this.dialog.open(NotificationComponent, dialogConfig);
  }
}
