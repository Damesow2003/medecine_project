import { Component } from '@angular/core';
import {CoreService} from "../../services/core.service";
import {Observable} from "rxjs";

@Component({
  selector: 'app-loading',
  templateUrl: './loading.component.html',
  styleUrl: './loading.component.scss'
})
export class LoadingComponent {
  isLoading$: Observable<boolean>;
  progress$: Observable<number>;

  constructor(private loadingService: CoreService) {
    this.isLoading$ = this.loadingService.isLoading$;
    this.progress$ = this.loadingService.progress$;
  }


}
