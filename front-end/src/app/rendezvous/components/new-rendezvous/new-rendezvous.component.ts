import {Component, Input, OnInit} from '@angular/core';
import {CabinetService} from "../../../cabinets/services/cabinet.service";
import {RendezvousModel} from "../../modeles/rendezvous.model";
import {ActivatedRoute} from "@angular/router";
import {catchError, map, Observable, of, tap} from "rxjs";
import {RendezvousService} from "../../services/rendezvous.service";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {RendezvousCabinetModel} from "../../../cabinets/modeles/rendezvousCabinet.model";
import {CabinetModel} from "../../../cabinets/modeles/cabinet.model";

@Component({
  selector: 'app-new-rendezvous',
  templateUrl: './new-rendezvous.component.html',
  styleUrl: './new-rendezvous.component.scss'
})
export class NewRendezvousComponent implements OnInit{

  constructor(private formBuilder:FormBuilder,
              private route:ActivatedRoute,
              private rendezvousService:RendezvousService) {
  }

    cabinetInfo!:RendezvousCabinetModel;
    mainForm!: FormGroup;
    dateRvCtrl!: FormControl;
    heureRvCtrl!: FormControl;
    dureeRvCtrl!: FormControl;

  rendezvousList!: RendezvousModel[]

  ngOnInit(): void {
 /*   const adminId = +this.route.snapshot.params['id'];
    this.cabinetService.getRendezvousCabinet(adminId).pipe(
        tap(reponse =>{
          this.rendezvousList = reponse.rendezvousList;
          console.log(this.rendezvousList)
        })
    ).subscribe()*/

      this.loadCabinet();
      this.initControlForms();

      this.mainForm = this.formBuilder.group({
          dateRv: this.dateRvCtrl.value,
          heureRv: this.heureRvCtrl.value,
          duree: this.dureeRvCtrl.value,
      })

  }
    loadCabinet(){
        this.rendezvousService.getCabinetRendezvous().pipe(
            tap(response=>{
                this.rendezvousList = response.rendezvousList;
                this.cabinetInfo = response;
            })
        ).subscribe();
    }

    initControlForms(){
      this.dateRvCtrl = this.formBuilder.control('',[Validators.required]);
      this.heureRvCtrl = this.formBuilder.control('',[Validators.required]);
      this.dureeRvCtrl = this.formBuilder.control(0,[Validators.required]);
    }

    onSave() {
         console.log(this.mainForm.value);
        this.rendezvousService.addRendezvous(this.mainForm.value, this.cabinetInfo.idCabinet).pipe(
            tap((response)=>{
                this.mainForm.reset();
                this.loadCabinet();
            }),
            catchError(err => {
                console.log("Error:", err);
                return of(false);
            })
        ).subscribe()

    }

    refreshList() {
        this.loadCabinet();
    }

    onDeleteRv(id:number) {
        this.rendezvousService.deleteRendezvousById(id).pipe(
            tap(()=>{
                this.loadCabinet();
            }),
            catchError(err => {
                console.log("Error:", err);
                return of(false);
            })
        ).subscribe();
    }
}
