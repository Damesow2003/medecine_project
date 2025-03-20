import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from "@angular/router";
import {AuthentificationService} from "../../authentification/services/authentification.service";
import {Injectable} from "@angular/core";


@Injectable()
export class AuthorizationGuard implements CanActivate{

  constructor(private authService:AuthentificationService,
              private router:Router) {
  }
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean{
    const roles = this.authService.roles || []; // Évite `undefined`

    if (roles.includes('medecin')) {
      return true;
    } else {
      this.router.navigateByUrl('/not-authorized');
      return false;
    }
  }

}
