import {Component, OnInit} from '@angular/core';
import {map, Observable} from "rxjs";
import {RendezvousModel} from "../../modeles/rendezvous.model";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-rendezvous-list',
  templateUrl: './rendezvous-list.component.html',
  styleUrl: './rendezvous-list.component.scss'
})
export class RendezvousListComponent implements OnInit {
  rendezvousList$!:Observable<RendezvousModel[]>;

  constructor(private route: ActivatedRoute) { }

    ngOnInit(): void {
      this.rendezvousList$ = this.route.data.pipe(
        map(data => data['rendezvousList']),
      )
    }

}
