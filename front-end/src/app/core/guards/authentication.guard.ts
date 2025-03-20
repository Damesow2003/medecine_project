import {
  ActivatedRouteSnapshot,
  CanActivate,
  GuardResult,
  MaybeAsync,
   Router,
  RouterStateSnapshot
} from "@angular/router";
import {AuthentificationService} from "../../authentification/services/authentification.service";
import {Injectable} from "@angular/core";

@Injectable()
export class AuthenticationGuard implements CanActivate{
  constructor(private authService:AuthentificationService,
              private route:Router) {
  }
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    if(this.authService.isAuthenticated){
      return true;
    }else{
      this.route.navigateByUrl('/auth/');
      return false;
    }
  }

}
