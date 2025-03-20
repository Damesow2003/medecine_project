import {Component, OnInit} from '@angular/core';
import {CabinetModel} from "../../modeles/cabinet.model";
import {map, Observable} from "rxjs";
import {CabinetsResolver} from "../../resolvers/cabinets.resolver";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-cabinets-list',
  templateUrl: './cabinets-list.component.html',
  standalone: false,
  styleUrl: './cabinets-list.component.scss'
})
export class CabinetsListComponent implements OnInit{
    cabinets$!:Observable<CabinetModel[]>;

    constructor(private cabinetResolver:CabinetsResolver,
                private route: ActivatedRoute){}
    ngOnInit(): void {
        this.cabinets$ = this.route.data.pipe(
          map(data => data['cabinets']),
        )
    }

}
