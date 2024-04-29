import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/utils/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  registerForm: FormGroup;

  userId: string
  password: string
  userName: string

  constructor(private router:Router, private fb: FormBuilder, private authservice: AuthService) { }

  ngOnInit(): void {
    this.registerForm = this.fb.group({
      userId: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(6)]],
      userName: ['', Validators.required]
    });
  }

  onSubmit() {

    this.authservice.register(this.registerForm.value)
    .subscribe(Response =>{console.log("Registered");},
    error => {console.log(error);
    }
    )
    
    console.log(this.registerForm.value);

    this.router.navigateByUrl("login");

  }

}
