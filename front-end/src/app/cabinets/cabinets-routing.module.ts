import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {CabinetsListComponent} from "./components/cabinets-list/cabinets-list.component";
import {CabinetsResolver} from "./resolvers/cabinets.resolver";
import {SingleCabinetComponent} from "./components/single-cabinet/single-cabinet.component";

const routes: Routes = [
  {path:'',component:CabinetsListComponent, resolve:{cabinets:CabinetsResolver}},
  {path:':id',component:SingleCabinetComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CabinetsRoutingModule { }
