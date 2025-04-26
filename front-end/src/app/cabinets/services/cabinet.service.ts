import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {CabinetModel} from "../modeles/cabinet.model";
import {environment} from "../../../environments/environment";
import {RendezvousCabinetModel} from "../modeles/rendezvousCabinet.model";

@Injectable()
 export class CabinetService{
    constructor(private http: HttpClient) { }

    getAllCabinets():Observable<CabinetModel[]>{
      return this.http.get<CabinetModel[]>(`${environment.apiUrl}/cabinets`)
    }
    getCabinetById(id:number):Observable<CabinetModel>{
      return this.http.get<CabinetModel>(`${environment.apiUrl}/cabinets/${id}`)
    }

    getListRendezvousByCabinetId(cabinetId:number):Observable<CabinetModel>{
      return this.http.get<CabinetModel>(`${environment.apiUrl}/cabinets/${cabinetId}`)
    }
    getRendezvousCabinet(idCabinet:number):Observable<RendezvousCabinetModel> {
    return this.http.get<RendezvousCabinetModel>(`${environment.apiUrl}/cabinets/${idCabinet}/rendezvous`);
    }
}
