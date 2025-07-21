import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MedecinRoutingModule } from './medecin-routing.module';
import { MedecineListComponent } from './components/medecine-list/medecine-list.component';
import { SingleMedecineComponent } from './components/single-medecine/single-medecine.component';
import {MedecinService} from "./services/medecin.service";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { MedecinRendezvousComponent } from './components/medecin-rendezvous/medecin-rendezvous.component';


@NgModule({
  declarations: [
    MedecineListComponent,
    SingleMedecineComponent,
    MedecinRendezvousComponent
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
