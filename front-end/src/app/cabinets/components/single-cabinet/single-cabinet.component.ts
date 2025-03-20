import {Component, OnInit} from '@angular/core';
import {Observable, tap} from "rxjs";
import {CabinetModel} from "../../modeles/cabinet.model";
import {CabinetService} from "../../services/cabinet.service";
import {ActivatedRoute, Router} from "@angular/router";
import {RendezvousModel} from "../../../rendezvous/modeles/rendezvous.model";
import {faCalendarAlt, faClock, faHourglassHalf} from "@fortawesome/free-solid-svg-icons";

@Component({
  selector: 'app-single-cabinet',
  templateUrl: './single-cabinet.component.html',
  styleUrl: './single-cabinet.component.scss'
})
export class SingleCabinetComponent implements OnInit{
  cabinet$!:Observable<CabinetModel>;
  rendezvousList!: RendezvousModel[];

  constructor(private cabinetService:CabinetService,
              private route:ActivatedRoute,
              private router:Router) {
  }

  ngOnInit(): void {
    const cabinetId = +this.route.snapshot.params['id'];
    this.cabinet$ = this.cabinetService.getCabinetById(cabinetId);
    this.cabinetService.getRendezvousCabinet(cabinetId).pipe(
      tap(response => {
        this.rendezvousList = response.rendezvousList;
      })
    ).subscribe()
  }

  protected readonly faCalendarAlt = faCalendarAlt;
  protected readonly faClock = faClock;
  protected readonly faHourglassHalf = faHourglassHalf;

  onPayRendezvous(id: number) {
    this.router.navigateByUrl(`/rendezvous/paiement/${id}`)
  }
}
