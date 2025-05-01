import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MedecinRoutingModule } from './medecin-routing.module';
import { MedecineListComponent } from './components/medecine-list/medecine-list.component';
import { SingleMedecineComponent } from './components/single-medecine/single-medecine.component';
import {MedecinService} from "./services/medecin.service";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";


@NgModule({
  declarations: [
    MedecineListComponent,
    SingleMedecineComponent
  ],
  imports: [
    CommonModule,
    MedecinRoutingModule,
    ReactiveFormsModule,
      FormsModule,
  ],
  providers: [
      MedecinService
  ]
})
export class MedecinModule { }
