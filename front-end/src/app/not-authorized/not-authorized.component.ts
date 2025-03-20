import { Component } from '@angular/core';
import {Router} from "@angular/router";
import {faExclamationTriangle, faSignInAlt} from "@fortawesome/free-solid-svg-icons";

@Component({
  selector: 'app-not-authorized',
  templateUrl: './not-authorized.component.html',
  styleUrl: './not-authorized.component.scss'
})
export class NotAuthorizedComponent {
  constructor(private router:Router) {
  }

  seConnecter() {
    this.router.navigateByUrl('/auth/login')
  }

  protected readonly faExclamationTriangle = faExclamationTriangle;
  protected readonly faSignInAlt = faSignInAlt;
}
