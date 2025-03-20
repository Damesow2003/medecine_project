import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AuthentificationRoutingModule } from './authentification-routing.module';
import { LoginComponent } from './components/login/login.component';
import { InscriptionComponent } from './components/inscription/inscription.component';
import {ShareModule} from "../share/share.module";
import {ReactiveFormsModule} from "@angular/forms";
import {AuthentificationService} from "./services/authentification.service";
import {HTTP_INTERCEPTORS} from "@angular/common/http";
import {AuthInterceptor} from "../core/interceptors/auth.interceptor";
import {CoreModule} from "../core/core.module";


@NgModule({
  declarations: [
    LoginComponent,
    InscriptionComponent
  ],
  imports: [
    CommonModule,
    AuthentificationRoutingModule,
    ShareModule,
    ReactiveFormsModule,
  ],
  providers:[
    AuthentificationService,
  ]
})
export class AuthentificationModule { }
