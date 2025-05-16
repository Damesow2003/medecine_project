import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {BehaviorSubject, catchError, delay, filter, map, Observable, of, switchMap, take, tap} from "rxjs";
import {Salle} from "../modeles/salle";
import {environment} from "../../../environments/environment.development";
import {Traitement} from "../modeles/traitement.model";


@Injectable()
export class SalleService {
    constructor(private http: HttpClient) {}

     _loading$= new BehaviorSubject<boolean>(false);
     _salles$ = new BehaviorSubject<Salle[]>([]);
     _traitements$ = new BehaviorSubject<Traitement[]>([]);
    get loading(){
        return this._loading$.asObservable();
    }

    get salles(){
        return this._salles$.asObservable();
    }

    get traitements(){
        return this._traitements$.asObservable();
    }

    setLoadingStatus(status:boolean){
        this._loading$.next(status);
    }

    getSalleFromServer(){
        this.setLoadingStatus(true);
        this.http.get<Salle[]>(`${environment.apiUrl}/salles`).pipe(
            delay(1000),
            tap((salles)=>{
                this.setLoadingStatus(false)
                this._salles$.next(salles);
            })
        ).subscribe();
    }

    getSalleById(id: number): Observable<Salle | undefined> {
        return this._salles$.pipe(
            map(salles => salles.find(salle => salle.idSalle === id)),
            switchMap(salle => {
                if (!salle) {
                    // Option 1: Recharger depuis le serveur si non trouvé
                    return this.http.get<Salle>(`${environment.apiUrl}/salles/${id}`).pipe(
                        catchError(() => of(undefined))
                    );
                }
                return of(salle);
            }),
            take(1)
        );
    }
    getTraitementFromServer(){
        this.setLoadingStatus(true);
        this.http.get<Traitement[]>(`${environment.apiUrl}/traitements`).pipe(
            delay(1000),
            tap((traitements)=>{
                this.setLoadingStatus(false)
                this._traitements$.next(traitements);
            })
        ).subscribe();
    }

    getTraitementById(id:number): Observable<Traitement | undefined>  {
        return this._traitements$.pipe(
            map((traitements)=>traitements.find(traitement=>traitement.idTraitement===id)),
            filter((traitement): traitement is Traitement => traitement !== undefined),
            take(1)
        )
    }
}