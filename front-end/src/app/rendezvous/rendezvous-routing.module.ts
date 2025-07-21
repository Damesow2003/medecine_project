import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {RendezvousListComponent} from "./components/rendezvous-list/rendezvous-list.component";
import {RendezvousResolver} from "./resolvers/rendezvous.resolver";
import {PaiementComponent} from "./components/paiement/paiement.component";
import {NewRendezvousComponent} from "./components/new-rendezvous/new-rendezvous.component";
import {RendezvousPatientComponent} from "./components/rendezvous-patient/rendezvous-patient.component";

const routes: Routes = [
  {path:'',component:RendezvousListComponent,resolve:{rendezvousList:RendezvousResolver}},
  {path:'paiement/:id',component:PaiementComponent},
  {path:'new',component:NewRendezvousComponent},
  {path:'rendezvous-patient',component:RendezvousPatientComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RendezvousRoutingModule { }
