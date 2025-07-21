import {Injectable} from "@angular/core";
import {BehaviorSubject, catchError, delay, filter, map, Observable, of, Subject, take, tap} from "rxjs";
import {Medecin} from "../modeles/medecin.model";
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {environment} from "../../../environments/environment.development";
import {RendezvousModel} from "../../rendezvous/modeles/rendezvous.model";

@Injectable()
export class MedecinService {
    constructor(private http: HttpClient) {}
    private lastMedecinLoad = 0
    _loading$ = new BehaviorSubject<boolean>(false);
    _medecins$ =  new BehaviorSubject<Medecin[]>([]);
    _error$ = new Subject<string>()

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

    getMedecinByMatricule(matricule: number): Observable<Medecin> {
        if(!this.lastMedecinLoad){
            this.getMedecinsFromServer();
        }
        return this.medecins$.pipe(
            map(medecins => medecins.find(medecin => medecin.matricule === matricule)),
            filter((medecin): medecin is Medecin => medecin !== undefined),
            take(1)
        );
    }
    getRendezvousByMedecin(matricule: number, cabinetId: number): Observable<RendezvousModel[]> {
        // Création des params et headers
        const params = new HttpParams()
            .set('cabinetId', cabinetId.toString());

        const headers = new HttpHeaders()
            .set('Content-Type', 'application/json');

        const options = {
            headers: headers,
            params: params
        };

        return this.http.get<RendezvousModel[]>(
            `${environment.apiUrl}/api/medecins/${matricule}/rendezvous`,
            options
        ).pipe(
            catchError(err => {
                console.error('Erreur lors de la récupération des rendez-vous:', err);
                this._error$.next("Erreur du chargement de rendez-vous");
                return of([]);
            })
        );
    }
    //clear cache
    clearCache(): void{
        this.lastMedecinLoad = 0;
    }
}