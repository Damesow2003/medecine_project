import {Component, OnInit} from '@angular/core';
import {AuthentificationService} from "./authentification/services/authentification.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent  implements OnInit{
  title = 'medecine_front';
  constructor(private authService:AuthentificationService) {
  }
  ngOnInit(): void {
    this.authService.loadJwtTokenFromLocalStorage();
    console.log("Token after loading", this.authService.accessToken)
  }
}
