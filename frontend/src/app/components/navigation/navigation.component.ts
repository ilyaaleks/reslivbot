import { Component, OnInit } from '@angular/core';
import {MatDialog, MatDialogConfig} from '@angular/material';
import {CityService} from '../../services/city.service';
import {NotificationComponent} from '../notification/notification.component';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  constructor(public dialog: MatDialog,
              public cityService: CityService) {
  }
  openDialog() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.width = '50%';
    this.dialog.open(NotificationComponent, dialogConfig);

  }
  ngOnInit() {
  }

}
