import {Component, Input, OnInit} from '@angular/core';
import {CabinetModel} from "../../modeles/cabinet.model";
import {CabinetService} from "../../services/cabinet.service";
import {ActivatedRoute, Router} from "@angular/router";

import {RendezvousModel} from "../../../rendezvous/modeles/rendezvous.model";

@Component({
  selector: 'app-cabinets-list-item',
  templateUrl: './cabinets-list-item.component.html',
  styleUrl: './cabinets-list-item.component.scss'
})
export class CabinetsListItemComponent implements OnInit {

  @Input() cabinet!:CabinetModel;
  rendezvousList!: RendezvousModel[];
  adresse!:string;
  nom!:string;

  constructor(private cabinetService:CabinetService,
              private route:ActivatedRoute,
              private router:Router) {

  }

  ngOnInit(): void {

  }
  onViewDetails(idCabinet:number) {
    this.router.navigateByUrl(`/cabinets/${idCabinet}`);
  }
}
