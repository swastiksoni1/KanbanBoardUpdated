import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, } from '@angular/forms';
import {HttpClient} from '@angular/common/http'
import { AuthService } from 'src/utils/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  loginStatus = false;
  token: string;
  
  constructor(private fb: FormBuilder, private http: HttpClient, private auth: AuthService ) { }

  ngOnInit() {
    this.loginForm = this.fb.group({
      userId: ['', [Validators.required]],
      password: ['', [Validators.required]]
    });
  }

  onSubmit() {
    if (this.loginForm.valid) {
      this.loginCustomer();
      console.log("hello");
  
      
    }
  }
  loginCustomer() {
    if (this.loginForm.valid) {
      const userData = {
        user_Id: this.loginForm.value.user_Id,
        password: this.loginForm.value.password
      };}
       this.auth.logIn(this.loginForm.value);
   

}
}


