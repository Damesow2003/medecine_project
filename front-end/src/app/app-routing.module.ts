import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./core/components/home/home.component";
import {AuthenticationGuard} from "./core/guards/authentication.guard";
import {AuthorizationGuard} from "./core/guards/authorization.guard";
import {NotAuthorizedComponent} from "./not-authorized/not-authorized.component";
import {PatientGuard} from "./core/guards/patient.guard";
import {MedecinGuard} from "./core/guards/medecin.guard";

const routes: Routes = [
  {path:'',component:HomeComponent},
  {path:'services', loadChildren:()=>import('./services/services.module').then(m=>m.ServicesModule),canActivate:[AuthenticationGuard]},
  {path:'cabinets',loadChildren:()=>import('./cabinets/cabinets.module').then(m=>m.CabinetsModule)},
  {path:'rendezvous',loadChildren:()=>import('./rendezvous/rendezvous.module').then(m=>m.RendezvousModule),canActivate:[AuthenticationGuard]},
  {path:'dashbord',loadChildren:()=>import('./dashbord/dashbord.module').then(m=>m.DashbordModule)},
  {path:'auth',loadChildren:()=>import('./authentification/authentification.module').then(m=>m.AuthentificationModule)},
  {path:'not-authorized',component:NotAuthorizedComponent},
  {path:'**',redirectTo:'/'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
