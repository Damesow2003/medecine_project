import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SalleRoutingModule } from './salle-routing.module';
import { SingleSalleComponent } from './components/single-salle/single-salle.component';
import { ListSalleComponent } from './components/list-salle/list-salle.component';
import {SalleService} from "./services/salle.service";
import {RouterModule} from "@angular/router";


@NgModule({
  declarations: [
    ListSalleComponent,
    SingleSalleComponent,
  ],
  imports: [
    CommonModule,
    SalleRoutingModule,
    RouterModule
  ],
  providers: [
      SalleService
  ]
})
export class SalleModule { }
