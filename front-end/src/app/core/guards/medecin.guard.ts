import {Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from "@angular/router";
import {AuthentificationService} from "../../authentification/services/authentification.service";

@Injectable()
export class MedecinGuard implements CanActivate{
    constructor(private authService:AuthentificationService,
                private router:Router) {
    }
    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
        const roles = this.authService.roles || []
        if(roles.includes('medecin')){
            return true;
        }else {
            this.router.navigateByUrl('/not-authorized');
            return false;
        }
    }

}