import {Component, Input, OnInit} from '@angular/core';
import {catchError, Observable, of, tap} from "rxjs";
import {RendezvousModel} from "../../modeles/rendezvous.model";
import {ActivatedRoute, Router} from "@angular/router";
import {RendezvousService} from "../../services/rendezvous.service";
import {PaiementModel} from "../../modeles/paiement.model";
import Swal from 'sweetalert2';

@Component({
  selector: 'app-paiement',
  templateUrl: './paiement.component.html',
  styleUrl: './paiement.component.scss'
})
export class PaiementComponent implements OnInit{
  paiements$!:Observable<PaiementModel>
  rendezvous$!:Observable<RendezvousModel>

  constructor(private router:Router,
              private route:ActivatedRoute,
              private rendezvousService:RendezvousService) {
  }
  idRendezvous = Number(this.route.snapshot.paramMap.get('id'))
  ngOnInit(): void {
    this.rendezvous$ = this.rendezvousService.getRendezvousById(this.idRendezvous);
    //ici j'utilise idRendezvous pour pouvoir acceder a un paiement
    //Pourquoi? -> car dans le back-end la cles etrangere idPaiement de rendezvous est la meme que celle de l'entite paiement
    this.paiements$ = this.rendezvousService.getPaiementById(this.idRendezvous);
  }

  onPaiementRendezvous() {
      this.rendezvousService.effectuerUnPaiement(this.idRendezvous).pipe(
        tap(()=>{
          Swal.fire({
            title:"Paiement reussi !",
            text:"Votre paiement a ete effectue avec success",
            icon:'success',
            confirmButtonText:'ok',
            confirmButtonColor:'#3085d6'
          })
        }),
        catchError(error =>{
          console.error("erreur de paiement: ", error);

          Swal.fire({
            title:'Echec du paiement',
            text:"Une erreur est survenue lors du paiement. Veuillez reessayer plus tard!",
            icon:'error',
            confirmButtonText:'ok',
            confirmButtonColor:'#d33'
          });
          return of(null); //permet de continuer le flux sans casser l'observable
        })
      ).subscribe()
  }

}
