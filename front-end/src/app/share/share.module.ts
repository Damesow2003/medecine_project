import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {FontAwesomeModule} from "@fortawesome/angular-fontawesome";
import { faCalendarAlt, faClock, faHourglassHalf } from '@fortawesome/free-solid-svg-icons';




@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    FontAwesomeModule
  ],
  exports: [
    FontAwesomeModule
  ]
})
export class ShareModule {

  faCalendarAlt = faCalendarAlt;
  faClock = faClock;
  faHourglassHalf = faHourglassHalf;
}
