import {ChangeDetectionStrategy, Component, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {Salle} from "../../modeles/salle";
import {SalleService} from "../../services/salle.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-list-salle',
  templateUrl: './list-salle.component.html',
  styleUrl: './list-salle.component.scss',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ListSalleComponent implements OnInit {
  loading$!: Observable<boolean>;
  salles$!:Observable<Salle[]>;

  constructor(private salleState: SalleService,
              private router: Router) {
  }

  ngOnInit(): void {
      this.initObservable();
      this.salleState.getSalleFromServer();

  }

  initObservable() {
    this.loading$ = this.salleState.loading;
    this.salles$ = this.salleState.salles;
  }

  onEdit(salle: Salle) {

  }

  onDelete(salle: Salle) {
    
  }

  onLoadSalleDetails(id:number) {
    this.router.navigateByUrl(`salles/${id}`);
  }
}
