import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {RendezvousModel} from "../modeles/rendezvous.model";
import {catchError, Observable, of} from "rxjs";
import {environment} from "../../../environments/environment";
import {PaiementModel} from "../modeles/paiement.model";

@Injectable()
export class RendezvousService {
  constructor(private http: HttpClient) { }


  getRendezvousList():Observable<RendezvousModel[]> {
      return this.http.get<RendezvousModel[]>(`${environment.apiUrl}/rendezvous`)
  }
  getRendezvousById(id:number):Observable<RendezvousModel>{
    return this.http.get<RendezvousModel>(`${environment.apiUrl}/rendezvous/${id}`);
  }
  addRendezvous():void{
  }
  getPaiementById(id:number):Observable<PaiementModel>{
    return this.http.get<PaiementModel>(`${environment.apiUrl}/paiements/${id}`);
  }
  effectuerUnPaiement(id:number):Observable<PaiementModel>{
    return this.http.put<PaiementModel>(`${environment.apiUrl}/paiements/effectuer-paiement/${id}`, {});
  }
}
