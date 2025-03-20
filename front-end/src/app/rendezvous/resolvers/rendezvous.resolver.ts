import {Injectable} from "@angular/core";
import {RendezvousService} from "../services/rendezvous.service";
import {ActivatedRouteSnapshot, MaybeAsync, Resolve, RouterStateSnapshot} from "@angular/router";
import {RendezvousModel} from "../modeles/rendezvous.model";
import {Observable} from "rxjs";

@Injectable()
export class RendezvousResolver implements Resolve<RendezvousModel[]> {
  constructor(private rendezvousService: RendezvousService) {
  }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<RendezvousModel[]> {
        return this.rendezvousService.getRendezvousList();
    }
}
