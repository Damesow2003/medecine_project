import {ChangeDetectionStrategy, Component, OnInit, TemplateRef} from '@angular/core';
import {Observable, switchMap} from "rxjs";
import {Traitement} from "../../modeles/traitement.model";
import {SalleService} from "../../services/salle.service";
import {ActivatedRoute} from "@angular/router";
import {Salle} from "../../modeles/salle";
import {TraitementAPI} from "../../modeles/traitementAPI.model";

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

  getNextRv(selectedRoom: Salle): TraitementAPI | null {
    if (!selectedRoom.traitements?.length) return null;

    const now = new Date();

    const futureRvs = selectedRoom.traitements
        .filter(t => this.hasValidRendezvous(t))
        .filter(t => {
          const rvDate = this.parseDateHeure(t.rendezvous!.dateHeure);
          return rvDate > now;
        })
        .sort((a, b) => {
          const dateA = this.parseDateHeure(a.rendezvous!.dateHeure);
          const dateB = this.parseDateHeure(b.rendezvous!.dateHeure);
          return dateA.getTime() - dateB.getTime();
        });

    return futureRvs[0] || null;
  }

  private hasValidRendezvous(traitement: TraitementAPI): boolean {
    return !!traitement.rendezvous?.dateHeure;
  }

  private parseDateHeure(dateHeure: string): Date {
    const [datePart, timePart] = dateHeure.split(' H: ');
    return new Date(`${datePart}T${timePart}`);
  }

  getTodayPatientsCount(selectedRoom: Salle): number {
    if (!selectedRoom.traitements.length) return 0;

    const today = new Date().toISOString().split('T')[0];
    return selectedRoom.traitements
        .filter(t => this.hasValidRendezvous(t))
        .filter(t => {
          const [datePart] = t.rendezvous!.dateHeure.split(' H: ');
          return datePart === today;
        }).length;
  }


  printDetails() {
    window.print()
  }

}
