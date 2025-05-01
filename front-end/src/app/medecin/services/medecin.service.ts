import {Injectable} from "@angular/core";
import {BehaviorSubject, delay, filter, map, Observable, take, tap} from "rxjs";
import {Medecin} from "../modeles/medecin.model";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment.development";

@Injectable()
export class MedecinService {
    constructor(private http: HttpClient) {}
    private lastMedecinLoad = 0
    _loading$ = new BehaviorSubject<boolean>(false);
    _medecins$ =  new BehaviorSubject<Medecin[]>([]);

    get medecins$(){
        return this._medecins$.asObservable();
    }

    get loading$(){
        return this._loading$.asObservable();
    }

    setLoadingStatus(loading: boolean){
        this._loading$.next(loading);
    }
    getMedecinsFromServer(){
        if(Date.now() - this.lastMedecinLoad<=3000){
            return ;
        }
        this.setLoadingStatus(true);

        this.http.get<Medecin[]>(`${environment.apiUrl}/auth/login/medecins`).pipe(
            delay(1000),
            tap(medecins => {
                this._medecins$.next(medecins);
                this.lastMedecinLoad = Date.now();
                this.setLoadingStatus(false);
            })
        ).subscribe();
    }

    getMedecinByMatricule(matricule: number): Observable<Medecin | undefined> {
        if(!this.lastMedecinLoad){
            this.getMedecinsFromServer();
        }
        return this.medecins$.pipe(
            map(medecins => medecins.find(medecin => medecin.matricule === matricule)),
            filter((medecin): medecin is Medecin => medecin !== undefined),
            take(1)
        );
    }
}