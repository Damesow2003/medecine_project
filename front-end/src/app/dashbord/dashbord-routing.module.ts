import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {CabinetDashbordComponent} from "./components/cabinet-dashbord/cabinet-dashbord.component";
import {AuthorizationGuard} from "../core/guards/authorization.guard";
import {AuthenticationGuard} from "../core/guards/authentication.guard";
import {PatientDashbordComponent} from "./components/patient-dashbord/patient-dashbord.component";
import {MedecineDashbordComponent} from "./components/medecine-dashbord/medecine-dashbord.component";

const routes: Routes = [
  {path:'cabinet',component:CabinetDashbordComponent,canActivate:[AuthorizationGuard]},
  {path:'patient',component:PatientDashbordComponent},
  {path:'medecin',component:MedecineDashbordComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DashbordRoutingModule { }
