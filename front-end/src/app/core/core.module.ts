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
import {CoreService} from "./services/core.service";
import { LoadingComponent } from './components/loading/loading.component';



@NgModule({
    declarations: [
        HomeComponent,
        HeaderComponent,
        FooterComponent,
        LoadingComponent
    ],
    exports: [
        HomeComponent,
        HeaderComponent,
        FooterComponent,
        LoadingComponent
    ],
    imports: [
        CommonModule,
        ShareModule,
        RouterModule,
        HttpClientModule,
        AuthentificationModule,

    ],
  providers:[
    {provide: HTTP_INTERCEPTORS,useClass:AuthInterceptor,multi:true},
    AuthenticationGuard,
    AuthorizationGuard,
    MedecinGuard,
    PatientGuard,
      CoreService
  ]
})
export class CoreModule { }
