import { Component } from '@angular/core';
import {faClinicMedical} from "@fortawesome/free-solid-svg-icons";


@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrl: './footer.component.scss'
})
export class FooterComponent {
  protected readonly faClinicMedical = faClinicMedical;
}
