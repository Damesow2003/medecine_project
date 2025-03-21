import { Component } from '@angular/core';
import {
  faCalendarCheck,
  faClinicMedical,
  faH,
  faHome,
  faHospital,
  faSignInAlt,
  faUser,
  faUserPlus
} from '@fortawesome/free-solid-svg-icons';
import {faBriefcaseMedical} from "@fortawesome/free-solid-svg-icons/faBriefcaseMedical";
import {AuthentificationService} from "../../../authentification/services/authentification.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss'
})
export class HeaderComponent {

  faHome = faHome;
  faUser = faUser;
  faHospital = faHospital;
  faCalendarCheck = faCalendarCheck;
  faUserPlus = faUserPlus;
  faSignInAlt = faSignInAlt;
  faClinicMedical = faClinicMedical;
  protected readonly faH = faH;
  protected readonly faBriefcaseMedical = faBriefcaseMedical;

  constructor(public authService:AuthentificationService,
              private router:Router) {
  }

  onLogOut() {
    this.authService.logout();
  }

  OnAdminProfil() {
    if(this.authService.roles.includes("medecin")){
      this.router.navigateByUrl('/dashbord/medecin')
    }else if(this.authService.roles.includes('patient')){
      this.router.navigateByUrl("/dashbord/patient" +
        "")
    }else if(this.authService.roles.includes('admin')){
      this.router.navigateByUrl("/dashbord/cabinet")
    }

  }
}
