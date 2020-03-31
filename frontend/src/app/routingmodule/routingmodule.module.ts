import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {CityComponent} from '../components/city/city.component';

const appRoutes: Routes = [
  {path: 'home', component: CityComponent},


];


@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forRoot(
      appRoutes, {enableTracing: true,onSameUrlNavigation: 'reload'}    )

  ],
  exports: [
    RouterModule
  ]
})
export class RoutingmoduleModule {
}
