import {ChangeDetectionStrategy, Component, OnInit} from '@angular/core';
import {MedecinService} from "../../services/medecin.service";
import {combineLatest, combineLatestAll, map, Observable, startWith} from 'rxjs';
import {Medecin} from "../../modeles/medecin.model";
import {Router} from "@angular/router";
import {FormBuilder, FormControl} from "@angular/forms";
import {MedecinSearchType} from "../../modeles/medecinSearchType.enum";

@Component({
  selector: 'app-medecine-list',
  templateUrl: './medecine-list.component.html',
  styleUrl: './medecine-list.component.scss',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class MedecineListComponent implements OnInit {
  medecins$!: Observable<Medecin[]>;
  loading$!: Observable<boolean>;

  searchCtrl!: FormControl;
  seachTypeCtrl!: FormControl;

  searchTypeOptions!:{
    value: MedecinSearchType,
    label: string
  }[]

  constructor(private medecinState:MedecinService,
              private router: Router,
              private formBuilder: FormBuilder,) {
  }

    ngOnInit(): void {
      this.initFormControl();
      this.initObservable();
      this.medecinState.getMedecinsFromServer();
    }

    initObservable(){
      this.loading$ = this.medecinState.loading$;


      const search = this.searchCtrl.valueChanges.pipe(
          startWith(this.searchCtrl.value),
          map(values => values.toLowerCase())
      )

      const searchType:Observable<MedecinSearchType> = this.seachTypeCtrl.valueChanges.pipe(
          startWith(this.seachTypeCtrl.value),
      )
      this.medecins$ = combineLatest([
        search,
        searchType,
        this.medecinState.medecins$
      ]).pipe(
          map(([search,searchType,medecins])=> medecins.filter(medecin => medecin[searchType]
              .toLowerCase()
              .includes(search as string))
             )
      )
    }

  onLoadMedecin(matricule:number) {
    this.router.navigateByUrl(`/medecins/${matricule}`);
  }

  initFormControl() {
    this.searchCtrl = this.formBuilder.control('');
    this.seachTypeCtrl = this.formBuilder.control(MedecinSearchType.SPECIALITE)
    this.searchTypeOptions = [
      {value:MedecinSearchType.PRENOM, label:'Prenom'},
      {value:MedecinSearchType.NOM,label: 'Nom'},
      {value:MedecinSearchType.SPECIALITE, label: 'Specialite'}
    ]
  }

  resetSearch() {
    this.searchCtrl.reset();
  }

}
