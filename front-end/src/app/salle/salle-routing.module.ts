import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ListSalleComponent} from "./components/list-salle/list-salle.component";
import {SingleSalleComponent} from "./components/single-salle/single-salle.component";

const routes: Routes = [
  {path:'',component:ListSalleComponent},
  {path:':id',component:SingleSalleComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SalleRoutingModule { }
