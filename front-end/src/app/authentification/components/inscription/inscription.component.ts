import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthentificationService} from "../../services/authentification.service";
import {map, Observable, startWith, tap} from "rxjs";
import {Route, Router} from "@angular/router";
import {ConfirmEqualValidator} from "../../validators/confirmEqual.validator";

@Component({
  selector: 'app-inscription',
  templateUrl: './inscription.component.html',
  styleUrl: './inscription.component.scss'
})
export class InscriptionComponent  implements OnInit{
  showEmailCtrl$!:Observable<boolean>;
  showPhoneCtrl$!:Observable<boolean>;
  showEmailError$!:Observable<boolean>;
  showPasswordError$!:Observable<boolean>
  loading$ = true;


  mainForm!:FormGroup;
  infosPersonnelForm!:FormGroup;
  infosConnexionForm!:FormGroup;
  emailForm!:FormGroup;
  firstnameCtrl!:FormControl;
  lastnameCtrl!:FormControl;
  dateDeNaissanceCtrl!:FormControl;
  adresseCtrl!:FormControl;
  contactPreferenceCtrl!:FormControl;
  emailCtrl!:FormControl;
  confirmEmailCtrl!:FormControl;
  phoneCtrl!:FormControl;
  usernameCtrl!:FormControl;
  passwordCtrl!:FormControl;
  confirmPassword!:FormControl;


  constructor(private formBuilder:FormBuilder,
              private authService:AuthentificationService,
              private router:Router) {
  }

  ngOnInit(): void {
    this.initFormControl();
    this.initFormGroup();
    this.initObservable();
    this.mainForm = this.formBuilder.group({
      infosPersonnel: this.infosPersonnelForm,
      contactPreference: this.contactPreferenceCtrl,
      email: this.emailForm,
      phone: this.phoneCtrl,
      infosConnexion: this.infosConnexionForm
    })

  }

  private initFormGroup(){
    this.infosPersonnelForm = this.formBuilder.group({
      firstname: this.firstnameCtrl,
      lastname: this.lastnameCtrl,
      dateDeNaissance: this.dateDeNaissanceCtrl,
      adresse: this.adresseCtrl
    });

    this.emailForm = this.formBuilder.group({
      email: this.emailCtrl,
      confirmEmail: this.confirmEmailCtrl
    },{
      validators: [ConfirmEqualValidator('email','confirmEmail')],
      updateOn: 'blur'
    });
    this.infosConnexionForm = this.formBuilder.group({
      username: this.usernameCtrl,
      password: this.passwordCtrl,
      confirmPassword: this.confirmPassword
    },{
      validators: [ConfirmEqualValidator('password','confirmPassword')],
      updateOn:'blur'
    });

  }

  private initFormControl(){
    this.firstnameCtrl = this.formBuilder.control('',[Validators.required, Validators.minLength(3)])
    this.lastnameCtrl = this.formBuilder.control('',[Validators.required, Validators.minLength(3)]);
    this.dateDeNaissanceCtrl = this.formBuilder.control('',[Validators.required]);
    this.adresseCtrl = this.formBuilder.control('',[Validators.required, Validators.minLength(7)]);
    this.contactPreferenceCtrl = this.formBuilder.control('email');
    this.emailCtrl = this.formBuilder.control('');
    this.confirmEmailCtrl = this.formBuilder.control('');
    this.phoneCtrl = this.formBuilder.control('',/*[Validators.minLength(9),Validators.maxLength(9)]*/);
    this.usernameCtrl = this.formBuilder.control('',[Validators.required])
    this.passwordCtrl = this.formBuilder.control('',[Validators.required]);
    this.confirmPassword = this.formBuilder.control('',[Validators.required]);
  }

  private initObservable(){
    this.showPhoneCtrl$ = this.contactPreferenceCtrl.valueChanges.pipe(
      startWith(this.contactPreferenceCtrl.value),
      map(preference=>preference==='phone'),
      tap(showPhone=>{
        if(showPhone){
          this.phoneCtrl.addValidators([Validators.maxLength(9), Validators.minLength(9),Validators.required])
        }else{
          this.phoneCtrl.clearValidators();
        }
        this.phoneCtrl.updateValueAndValidity();
      })
    )

    this.showEmailCtrl$ = this.contactPreferenceCtrl.valueChanges.pipe(
      startWith(this.contactPreferenceCtrl.value),
      map(preference => preference==='email'),
      tap(showEmail=>{
        if(showEmail){
          this.emailCtrl.addValidators([Validators.required,Validators.email]);
          this.confirmEmailCtrl.addValidators([Validators.required,Validators.email]);
          this.emailForm.setValidators(ConfirmEqualValidator('email', 'confirmEmail'));
        }else{
          this.emailCtrl.clearValidators();
          this.confirmEmailCtrl.clearValidators()
          this.emailForm.clearValidators();
        }
        this.emailCtrl.updateValueAndValidity();
        this.confirmEmailCtrl.updateValueAndValidity();
        this.emailForm.updateValueAndValidity();
      })
    );

    this.showEmailError$ = this.emailForm.statusChanges.pipe(
      map(()=> this.emailForm.hasError('confirmEqual'))
    );
    this.showPasswordError$ = this.infosConnexionForm.statusChanges.pipe(
      map(status=> status==='INVALID' && this.passwordCtrl.value
                                                               && this.confirmPassword.value
                                                                && this.infosConnexionForm.hasError('confirmEqual'))
    )
  }

  onSubmit() {
    const formValue = this.mainForm.value;

    const formattedData = {
      prenom: formValue.infosPersonnel.firstname,
      nom: formValue.infosPersonnel.lastname,
      email: formValue.email.email,
      confirmEmail: formValue.email.confirmEmail,
      telephone: formValue.phone,
      adresse: formValue.infosPersonnel.adresse,
      contactPreference: formValue.contactPreference,
      password: formValue.infosConnexion.password,
      confirmPassword: formValue.infosConnexion.confirmPassword,
      username: formValue.infosConnexion.username,
      dateDeNaissance: formValue.infosPersonnel.dateDeNaissance,
      role: "patient"  // Ajoute le rôle par défaut
    };

    console.log("Données envoys :", formattedData);

    this.authService.addPatient(formattedData).pipe(
      tap((saved) => {
        if (saved) {
          this.resetForm();
          console.log("Success");
          this.router.navigateByUrl('/');
        } else {
          console.log("Erreur: l'inscription ne s'est pas déroulée comme prévu !!");
        }
      })
    ).subscribe();
  }
  private resetForm(){
    this.mainForm.reset()
  }

  getControlErrors(ctrl:AbstractControl){
    if(ctrl.hasError('required')){
      return "Ce champs est requis !";
    }else if(ctrl.hasError('minlength')){
      return "Ce champs ne contient pas assez de caracteres !"
    }else if(ctrl.hasError('maxlength')){
      return "Ce champs contient trop de caracteres !"
    }else if(ctrl.hasError('confirmEqual')){
      return "les 2 champs ne se ressemble pas !"
    }else if(ctrl.hasError('email')){
      return "Veuillez entrez une adresse email valide"
    }
    return "Ce champs contient une Erreur !"
  }
}
