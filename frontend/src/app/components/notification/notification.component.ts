import {Component, Inject, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {CityService} from '../../services/city.service';
import {CityModel} from '../../model/city-model';
import {MAT_DIALOG_DATA} from '@angular/material';

@Component({
  selector: 'app-notification',
  templateUrl: './notification.component.html',
  styleUrls: ['./notification.component.css']
})
export class NotificationComponent implements OnInit {
  public postForm: FormGroup;

  constructor(private cityService: CityService,
              @Inject(MAT_DIALOG_DATA) public data: any) {
  }

  ngOnInit() {
    this.postForm = new FormGroup({
      name: new FormControl('', [Validators.required]),
      description: new FormControl(null, [Validators.required])
    });
  }

  addCity(): void {
    let city: CityModel = {
      id: null,
      name: this.postForm.controls['name'].value,
      description: this.postForm.controls['description'].value
    };
    if(this.data!==null){
      city.id=this.data.cityId;
      this.cityService.updateCity(city).subscribe((city:CityModel)=>{
        this.cityService.updatedCity.next(city);
      })
      return;
    }

    this.cityService.saveCity(city).subscribe((city:CityModel)=>{
      this.cityService.savedCity.next(city);
    });
  }
}
