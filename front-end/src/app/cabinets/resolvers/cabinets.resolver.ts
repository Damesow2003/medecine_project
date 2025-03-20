import {Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, MaybeAsync, Resolve, RouterStateSnapshot} from "@angular/router";
import {CabinetModel} from "../modeles/cabinet.model";
import {Observable} from "rxjs";
import {CabinetService} from "../services/cabinet.service";

@Injectable()
export class CabinetsResolver implements Resolve<CabinetModel[]> {
  constructor(private cabinetService:CabinetService) {
  }
    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot):Observable<CabinetModel[]>{
        return this.cabinetService.getAllCabinets();
    }

}
