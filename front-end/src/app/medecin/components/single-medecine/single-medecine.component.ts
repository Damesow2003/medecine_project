import {ChangeDetectionStrategy, Component, OnInit} from '@angular/core';
import {Medecin} from "../../modeles/medecin.model";
import {filter, Observable, switchMap, take, tap} from "rxjs";
import {MedecinService} from "../../services/medecin.service";
import {ActivatedRoute} from "@angular/router";
import {FormBuilder, FormControl} from "@angular/forms";

@Component({
  selector: 'app-single-medecine',
  templateUrl: './single-medecine.component.html',
  styleUrl: './single-medecine.component.scss',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class SingleMedecineComponent implements OnInit {
  loading$!: Observable<boolean>;
  medecin$!: Observable<Medecin | undefined>;


  constructor(private medecinState: MedecinService,
              private router: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.initObservable();
  }


  initObservable() {
    this.loading$ = this.medecinState.loading$;
    this.medecin$ = this.router.params.pipe(
        switchMap(params => {
          const matricule = +params['matricule'];
          return this.medecinState.getMedecinByMatricule(matricule)
        }),
    )
  }

}
