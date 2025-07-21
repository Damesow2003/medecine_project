import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {MedecineListComponent} from "./components/medecine-list/medecine-list.component";
import {SingleMedecineComponent} from "./components/single-medecine/single-medecine.component";
import {MedecinRendezvousComponent} from "./components/medecin-rendezvous/medecin-rendezvous.component";

const routes: Routes = [
  {path: '',component:MedecineListComponent},
  {path:':matricule/rendezvous', component:MedecinRendezvousComponent},
  {path:':matricule',component:SingleMedecineComponent},

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MedecinRoutingModule { }
