import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CabinetsRoutingModule } from './cabinets-routing.module';

import {ShareModule} from "../share/share.module";
import {CabinetsListItemComponent} from "./components/cabinets-list-item/cabinets-list-item.component";
import {CabinetsListComponent} from "./components/cabinets-list/cabinets-list.component";
import {CabinetService} from "./services/cabinet.service";
import {CabinetsResolver} from "./resolvers/cabinets.resolver";
import { SingleCabinetComponent } from './components/single-cabinet/single-cabinet.component';


@NgModule({
  declarations: [
    CabinetsListItemComponent,
    CabinetsListComponent,
    SingleCabinetComponent,
  ],
  imports: [
    CommonModule,
    CabinetsRoutingModule,
    ShareModule,
  ],
  exports: [
    CabinetsListItemComponent,
    SingleCabinetComponent
  ],
  providers:[
      CabinetService,
      CabinetsResolver
  ]
})
export class CabinetsModule { }
