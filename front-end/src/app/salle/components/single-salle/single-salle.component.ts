import {ChangeDetectionStrategy, Component, OnInit, TemplateRef} from '@angular/core';
import {Observable, switchMap} from "rxjs";
import {Traitement} from "../../modeles/traitement.model";
import {SalleService} from "../../services/salle.service";
import {ActivatedRoute} from "@angular/router";
import {Salle} from "../../modeles/salle";
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-single-salle',
  templateUrl: './single-salle.component.html',
  styleUrl: './single-salle.component.scss',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class SingleSalleComponent implements OnInit {

  loading$!: Observable<boolean>;
  traitement$!:Observable<Traitement | undefined>
  salle$!: Observable<Salle | undefined>

  constructor(private salleState: SalleService,
              private route: ActivatedRoute,
  ){

  }

  ngOnInit(): void {
    this.initObservable();
  }

  initObservable(){
    this.loading$ = this.salleState.loading;
   /* this.traitement$ = this.route.params.pipe(
        switchMap(params => {
          const idTraitement = +params['id'];
          return this.traitementState.getTraitementById(idTraitement)
        })
    )*/
    this.salle$ = this.route.params.pipe(
        switchMap(params => {
          const idSalle = +params['id'];
          return this.salleState.getSalleById(idSalle);
        })
    )
  }

  getNextRv(selectedRoom: Salle) {
    if (!selectedRoom.traitements || selectedRoom.traitements.length === 0) {
      return null;
    }

    // Trouver le rendez-vous le plus proche (date/heure dans le futur)
    const now = new Date();
    const futureRvs = selectedRoom.traitements
        .filter(t => {
          const rvDate = new Date(`${t.rendezvous.dateRv}T${t.rendezvous.heureRv}`);
          return rvDate > now;
        })
        .sort((a, b) => {
          const dateA = new Date(`${a.rendezvous.dateRv}T${a.rendezvous.heureRv}`);
          const dateB = new Date(`${b.rendezvous.dateRv}T${b.rendezvous.heureRv}`);
          return dateA.getTime() - dateB.getTime();
        });

    return futureRvs.length > 0 ? futureRvs[0] : null;
  }
  getTodayPatientsCount(selectedRoom: Salle) {
    const today = new Date().toISOString().split('T')[0];
    return selectedRoom.traitements.filter(traitement => traitement.rendezvous.dateRv === today).length;
  }



  printDetails() {
    window.print()
  }

}
