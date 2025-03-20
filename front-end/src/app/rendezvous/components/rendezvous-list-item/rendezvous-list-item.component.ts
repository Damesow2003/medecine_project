import {Component, Input} from '@angular/core';
import {RendezvousModel} from "../../modeles/rendezvous.model";

@Component({
  selector: 'app-rendezvous-list-item',
  templateUrl: './rendezvous-list-item.component.html',
  styleUrl: './rendezvous-list-item.component.scss'
})
export class RendezvousListItemComponent {
  @Input() rendezvous!:RendezvousModel;
  select= false;


  onRendezvousClick() {
    this.select = !this.select
  }
}
