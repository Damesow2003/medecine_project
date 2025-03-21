import {Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot} from "@angular/router";
import {AuthentificationService} from "../../authentification/services/authentification.service";

@Injectable()
export class PatientGuard implements CanActivate{

  constructor(private authService:AuthentificationService) {
  }
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    const roles = this.authService.roles || [];
    if(roles.includes("patient")){
     return  true
    }else{
      return false
    }
  }

}
