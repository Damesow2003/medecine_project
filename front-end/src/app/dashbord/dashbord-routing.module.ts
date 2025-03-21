import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {CabinetDashbordComponent} from "./components/cabinet-dashbord/cabinet-dashbord.component";
import {AuthorizationGuard} from "../core/guards/authorization.guard";
import {AuthenticationGuard} from "../core/guards/authentication.guard";
import {PatientDashbordComponent} from "./components/patient-dashbord/patient-dashbord.component";
import {MedecineDashbordComponent} from "./components/medecine-dashbord/medecine-dashbord.component";
import {PatientGuard} from "../core/guards/patient.guard";
import {MedecinGuard} from "../core/guards/medecin.guard";

const routes: Routes = [
  {path:'cabinet',component:CabinetDashbordComponent,canActivate:[AuthorizationGuard]},
  {path:'patient',component:PatientDashbordComponent,canActivate:[PatientGuard]},
  {path:'medecin',component:MedecineDashbordComponent,canActivate:[MedecinGuard]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DashbordRoutingModule { }
