import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {RendezvousListComponent} from "./components/rendezvous-list/rendezvous-list.component";
import {RendezvousResolver} from "./resolvers/rendezvous.resolver";
import {PaiementComponent} from "./components/paiement/paiement.component";
import {NewRendezvousComponent} from "./components/new-rendezvous/new-rendezvous.component";

const routes: Routes = [
  {path:'',component:RendezvousListComponent,resolve:{rendezvousList:RendezvousResolver}},
  {path:'paiement/:id',component:PaiementComponent},
  {path:'new/:id',component:NewRendezvousComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RendezvousRoutingModule { }
