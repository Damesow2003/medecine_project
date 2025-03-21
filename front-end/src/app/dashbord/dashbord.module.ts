import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DashbordRoutingModule } from './dashbord-routing.module';
import { CabinetDashbordComponent } from './components/cabinet-dashbord/cabinet-dashbord.component';
import {ShareModule} from "../share/share.module";
import { PatientDashbordComponent } from './components/patient-dashbord/patient-dashbord.component';
import { MedecineDashbordComponent } from './components/medecine-dashbord/medecine-dashbord.component';


@NgModule({
  declarations: [
    CabinetDashbordComponent,
    PatientDashbordComponent,
    MedecineDashbordComponent,
  ],
  imports: [
    CommonModule,
    DashbordRoutingModule,
    ShareModule
  ]
})
export class DashbordModule { }
