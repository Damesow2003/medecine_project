import {Component, OnInit} from '@angular/core';
import {AuthentificationService} from "../../../authentification/services/authentification.service";
import {Medecin} from "../../../medecin/modeles/medecin.model";
import {MedecinService} from "../../../medecin/services/medecin.service";
import {catchError, Observable, of, tap} from "rxjs";

@Component({
  selector: 'app-medecine-dashbord',
  templateUrl: './medecine-dashbord.component.html',
  styleUrl: './medecine-dashbord.component.scss'
})
export class MedecineDashbordComponent  implements OnInit{
  matricule!: number;
  cabinetId: number | null = null; // Permet null ou number
  role!: any;
  medecin$!: Observable<Medecin | undefined>;

  constructor(
      private auth: AuthentificationService,
      private medecinService: MedecinService
  ) {}

  ngOnInit() {
    this.matricule = this.auth.id;
    this.role = this.auth.roles;
    this.loadCurrentUserMedecin(this.matricule, this.role);
  }

  loadCurrentUserMedecin(matricule: number, role: any) {
    if (matricule != null && role != null) {
      if (role.includes('medecin')) {
        this.medecin$ = this.medecinService.getMedecinByMatricule(matricule).pipe(
            tap(medecin => {
              console.log("Medecin recu: ",medecin);
              if (medecin?.cabinets?.length) {
                this.cabinetId = medecin.cabinets[0].idCabinet;
                console.warn(this.cabinetId.toString())
              } else {
                console.warn('Médecin sans cabinet associé');
                this.cabinetId = null;
              }
            }),
            catchError(error => {
              console.error('Erreur chargement médecin', error);
              return of(undefined);
            })
        );
      }
    }
  }

}
