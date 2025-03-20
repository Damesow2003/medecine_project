import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ServicesRoutingModule } from './services-routing.module';
import { ServiceComponent } from './components/service/service.component';
import {ShareModule} from "../share/share.module";


@NgModule({
  declarations: [
    ServiceComponent
  ],
  imports: [
    CommonModule,
    ServicesRoutingModule,
    ShareModule
  ]
})
export class ServicesModule { }
