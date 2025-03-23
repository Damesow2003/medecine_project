import {Inject, Injectable, PLATFORM_ID} from "@angular/core";
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {catchError, map, Observable, of} from "rxjs";
import {environment} from "../../../environments/environment";
import {PatientModel} from "../modeles/patient.model";
import {FormControl} from "@angular/forms";
import {jwtDecode} from "jwt-decode";
import {Router} from "@angular/router";
import {isPlatformBrowser} from "@angular/common";

@Injectable()
export class AuthentificationService{
  showItem:boolean = true;
  isAuthenticated:boolean=false;
  username!:string;
  password!:string;
  accessToken!:any;
  roles!:any;

  constructor(private http:HttpClient,
              private router:Router,
              @Inject(PLATFORM_ID) private platformId: Object) {
  }

  addPatient(formValue: {
    dateDeNaissance: any;
    password: any;
    role: string;
    contactPreference: any;
    adresse: any;
    confirmPassword: any;
    telephone: FormControl<any>;
    confirmEmail: any;
    prenom: FormControl<any>;
    nom: FormControl<any>;
    email: any;
    username: string
  }):Observable<boolean>{
      let body = {
        nom: formValue.nom,
        prenom: formValue.prenom,
        email: formValue.email,
        password: formValue.password,
        telephone: formValue.telephone,
        adresse: formValue.adresse,
        role: "patient",
        confirmEmail: formValue.confirmEmail,
        confirmPassword: formValue.confirmPassword,
        contactPreference: formValue.contactPreference,
        username: formValue.username,
        dateDeNaissance: formValue.dateDeNaissance
      }
      console.log(formValue);
      return this.http.post<PatientModel>(`${environment.apiUrl}/auth/login/signup`,body).pipe(
        map(reponse=>{
          return true;
        }),
        catchError(err => {
          console.log("error: ",err);
          return of(false);
        })
      )

  }

  login(username:string,password:string){
  let options = {
      headers: new HttpHeaders().set("Content-Type","application/x-www-form-urlencoded")
    };

    let params = new HttpParams().set('username',username).set('password',password);

      return this.http.post(`${environment.apiUrl}/auth/login/sigin`,params,options);
  }


  loadProfile(data: any) {
    this.isAuthenticated = true;
    this.showItem = false;
    this.accessToken = data['access-token'];
    let jwtdecoder:any = jwtDecode(this.accessToken);
    this.username = jwtdecoder.sub;
    this.roles = jwtdecoder.scope;
    if (typeof window != 'undefined'){window.localStorage.setItem('jwt-token',this.accessToken)};
  }

  logout() {
    this.isAuthenticated = false;
    this.accessToken = undefined
    this.username="";
    this.roles=undefined;
    if(typeof window!='undefined'){
      window.localStorage.removeItem('jwt-token');
    }
    this.showItem = true;
    this.router.navigateByUrl('/home')

  }

  loadJwtTokenFromLocalStorage() {
    if (isPlatformBrowser(this.platformId)) {
      let token = window.localStorage.getItem('jwt-token');
      console.log("Token charger au niveau de localStorage: ", token)
      if (token) {
        this.loadProfile({'access-token': token})
      }
    }
  }
}
