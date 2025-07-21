import {Component, Input, OnInit} from '@angular/core';
import {faBell, faCalendarCheck, faCreditCard, faHome, faPills, faSignOutAlt} from "@fortawesome/free-solid-svg-icons";
import {faBriefcaseMedical} from "@fortawesome/free-solid-svg-icons/faBriefcaseMedical";
import {AuthentificationService} from "../../../authentification/services/authentification.service";

@Component({
  selector: 'app-patient-dashbord',
  templateUrl: './patient-dashbord.component.html',
  styleUrl: './patient-dashbord.component.scss'
})
export class PatientDashbordComponent implements OnInit {
  username!:String;

  constructor(private authService:AuthentificationService) {
  }

  ngOnInit(): void {
       this.username = this.authService.username;
    }

  protected readonly faHome = faHome;
  protected readonly faCalendarCheck = faCalendarCheck;
  protected readonly faBriefcaseMedical = faBriefcaseMedical;
  protected readonly faBell = faBell;
  protected readonly faCreditCard = faCreditCard;
  protected readonly faSignOutAlt = faSignOutAlt;

  logout() {
    this.authService.logout();

  }

  protected readonly faPills = faPills;
}
