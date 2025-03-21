import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthentificationService} from "../../services/authentification.service";
import {catchError, tap} from "rxjs";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent implements OnInit{
  isAuthentified!:boolean;
  constructor(private formBuilder:FormBuilder,
              private authenficationService:AuthentificationService,
              private router:Router) {
  }

  loginForm!:FormGroup;
  usernameCtrl!:FormControl;
  passwordCtrl!:FormControl;


  ngOnInit(): void {
    this.initControlForm();
    this.loginForm = this.formBuilder.group({
      username: this.usernameCtrl.value,
      password: this.passwordCtrl.value
    })
  }

  private initControlForm(){
    this.usernameCtrl = this.formBuilder.control('',[Validators.required, Validators.email]);
    this.passwordCtrl = this.formBuilder.control('',[Validators.required])
  }

  onLogin() {
    console.log(this.loginForm.value);
    let username = this.loginForm.value.username;
    console.log(username)
    let password = this.loginForm.value.password;

    this.authenficationService.login(username,password).pipe(
      tap((data)=>{
        console.log(data);
        this.authenficationService.loadProfile(data);

        window.location.href="http://localhost:4200/"
        //this.router.navigateByUrl('/home')

      }),
      /*catchError(error => {
        console.log("Err: ",error)
      })*/
    ).subscribe()

    this.isAuthentified = !this.loginForm.invalid;
  }

}
