import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {ModalModule} from 'ngx-bootstrap/modal';
import {AppComponent} from './app.component';
import {CityComponent} from './components/city/city.component';
import {CityService} from './services/city.service';
import {NavigationComponent} from './components/navigation/navigation.component';
import {HttpClientModule} from '@angular/common/http';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MAT_DIALOG_DEFAULT_OPTIONS, MatDialogModule} from '@angular/material';
import {ScrollingModule} from '@angular/cdk/scrolling';
import { NotificationComponent } from './components/notification/notification.component';
import {RoutingmoduleModule} from './routingmodule/routingmodule.module';
import {ReactiveFormsModule} from '@angular/forms';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

@NgModule({
  entryComponents: [NotificationComponent],
  declarations: [
    AppComponent,
    CityComponent,
    NavigationComponent,
    NotificationComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    ModalModule.forRoot(),
    MatDialogModule,
    BrowserAnimationsModule,
    RoutingmoduleModule,
    ReactiveFormsModule,
    NgbModule
  ],
  providers: [{provide: MAT_DIALOG_DEFAULT_OPTIONS, useValue: {hasBackdrop: false}}, CityService],
  bootstrap: [AppComponent],
  exports: [MatDialogModule, ScrollingModule]
})
export class AppModule {
}
