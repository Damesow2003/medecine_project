import {Component, OnInit} from '@angular/core';
import {CabinetService} from "../../../cabinets/services/cabinet.service";
import {RendezvousModel} from "../../modeles/rendezvous.model";
import {ActivatedRoute} from "@angular/router";
import {tap} from "rxjs";

@Component({
  selector: 'app-new-rendezvous',
  templateUrl: './new-rendezvous.component.html',
  styleUrl: './new-rendezvous.component.scss'
})
export class NewRendezvousComponent implements OnInit{
  constructor(private cabinetService:CabinetService,
              private route:ActivatedRoute) {
  }

  rendezvousList!: RendezvousModel[]

  ngOnInit(): void {
    const adminId = +this.route.snapshot.params['id'];
    this.cabinetService.getRendezvousCabinet(adminId).pipe(
        tap(reponse =>{
          this.rendezvousList = reponse.rendezvousList;
          console.log(this.rendezvousList)
        })
    ).subscribe()
  }
}
