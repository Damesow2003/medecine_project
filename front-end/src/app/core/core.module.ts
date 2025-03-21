import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './components/home/home.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {RouterModule} from "@angular/router";
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import {AuthInterceptor} from "./interceptors/auth.interceptor";
import {AuthentificationModule} from "../authentification/authentification.module";
import {AuthenticationGuard} from "./guards/authentication.guard";
import {AuthorizationGuard} from "./guards/authorization.guard";
import {ShareModule} from "../share/share.module";
import {MedecinGuard} from "./guards/medecin.guard";
import {PatientGuard} from "./guards/patient.guard";



@NgModule({
    declarations: [
        HomeComponent,
        HeaderComponent,
        FooterComponent
    ],
  exports: [
    HomeComponent,
    HeaderComponent,
    FooterComponent
  ],
    imports: [
        CommonModule,
        ShareModule,
        RouterModule,
        HttpClientModule,
        AuthentificationModule
    ],
  providers:[
    {provide: HTTP_INTERCEPTORS,useClass:AuthInterceptor,multi:true},
    AuthenticationGuard,
    AuthorizationGuard,
    MedecinGuard,
    PatientGuard
  ]
})
export class CoreModule { }
