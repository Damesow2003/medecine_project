import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RendezvousRoutingModule } from './rendezvous-routing.module';
import { RendezvousListComponent } from './components/rendezvous-list/rendezvous-list.component';
import { RendezvousListItemComponent } from './components/rendezvous-list-item/rendezvous-list-item.component';
import {RendezvousService} from "./services/rendezvous.service";
import {RendezvousResolver} from "./resolvers/rendezvous.resolver";
import {ShareModule} from "../share/share.module";
import { PaiementComponent } from './components/paiement/paiement.component';



@NgModule({
  declarations: [
    RendezvousListComponent,
    RendezvousListItemComponent,
    PaiementComponent
  ],
  imports: [
    CommonModule,
    RendezvousRoutingModule,
    ShareModule,
  ],
    exports: [
        RendezvousListItemComponent

    ],
  providers: [
    RendezvousService,
    RendezvousResolver
  ]
})
export class RendezvousModule { }
