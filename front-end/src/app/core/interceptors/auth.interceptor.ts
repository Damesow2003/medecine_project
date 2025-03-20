import { HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { catchError, Observable, throwError } from "rxjs";
import { Injectable, Injector } from "@angular/core";
import { AuthentificationService } from "../../authentification/services/authentification.service";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private injector: Injector) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    console.log(":::::::::");
    // Obtenir AuthentificationService dynamiquement pour éviter la dépendance circulaire? pourkoi c pour eviter un boucle
    // car AuthentificationService utilise HttpClient, qui lui-même passe par HTTP_INTERCEPTORS.
    //En injectant Injector plutôt que AuthentificationService directement, l'instance
    // d'AuthentificationService n'est obtenue qu'au moment de l'exécution de intercept, ce qui casse la boucle circulaire.
    if (!req.url.includes("/auth")) {
      const authService = this.injector.get(AuthentificationService);

      let token = authService.accessToken;

      // Vérifie si `window` est défini avant d'utiliser localStorage
      if (typeof window !== 'undefined') {
        token = token || window.localStorage.getItem('jwt-token');
      }

      if (!token) {
        console.warn('No token found, sending request without Authorization header.');
        return next.handle(req);
      }

      const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
      const modifiedReq = req.clone({ headers });

      return next.handle(modifiedReq).pipe(
        catchError(err => {
          if (err.status === 401) {
            console.error("Unauthorized request: Invalid or expired token.");
            authService.logout();
          }
          return throwError(() => new Error(err.message));
        })
      );
    } else {
      return next.handle(req);
    }
  }
}

