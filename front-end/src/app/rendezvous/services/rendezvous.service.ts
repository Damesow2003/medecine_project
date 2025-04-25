import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {RendezvousModel} from "../modeles/rendezvous.model";
import {catchError, Observable, of} from "rxjs";
import {environment} from "../../../environments/environment.development";
import {PaiementModel} from "../modeles/paiement.model";
import {CabinetModel} from "../../cabinets/modeles/cabinet.model";
import {RendezvousCabinetModel} from "../../cabinets/modeles/rendezvousCabinet.model";
import {RendezvousProxy} from "../modeles/rendezvousProxy.model";

@Injectable()
export class RendezvousService {
  constructor(private http: HttpClient) { }


  getRendezvousList():Observable<RendezvousModel[]> {
      return this.http.get<RendezvousModel[]>(`${environment.apiUrl}/rendezvous`)
  }
  getRendezvousById(id:number):Observable<RendezvousModel>{
    return this.http.get<RendezvousModel>(`${environment.apiUrl}/rendezvous/${id}`);
  }
  addRendezvous(formValue:{dateRv:string,heureRv:string,duree:number},idCabinet:number):Observable<RendezvousProxy>{
    return this.http.post<RendezvousProxy>(`${environment.apiUrl}/rendezvous`,{
      ...formValue,
      idCabinet: idCabinet
    } )
  }
  getPaiementById(id:number):Observable<PaiementModel>{
    return this.http.get<PaiementModel>(`${environment.apiUrl}/paiements/${id}`);
  }
  effectuerUnPaiement(id:number):Observable<PaiementModel>{
    return this.http.put<PaiementModel>(`${environment.apiUrl}/paiements/effectuer-paiement/${id}`, {});
  }
  getCabinetRendezvous():Observable<RendezvousCabinetModel> {
   return  this.http.get<RendezvousCabinetModel>(`${environment.apiUrl}/cabinets/rendezvous`);
  }

  deleteRendezvousById(id:number):Observable<boolean>{
    return this.http.delete<boolean>(`${environment.apiUrl}/rendezvous/${id}`);
  }
}

