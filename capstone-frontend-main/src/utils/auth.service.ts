import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { TokenService } from './token.service';

import { Router } from '@angular/router';


@Injectable({
    providedIn: 'root'
})

export class AuthService {

endpoint: string = "http://localhost:9000/api/v2";
headers = new HttpHeaders().set('Content-Type', 'appliction/json');
currendId:any


setCurrentUser(userId:any) {
  localStorage.setItem('user', userId);
}

getCurrentUser(){
  return localStorage.getItem('user');
}

clearCurrentUser(){
 localStorage.removeItem('user');
}

  constructor(private http: HttpClient, public router: Router, private token:TokenService) {}

logIn(userData: any ) {
  console.log("message from auth service");
  console.log(userData);
  return this.http
    .post<any>('http://localhost:9000/api/v2/login', userData)   
    .subscribe((res ) => {
    this.token.setGeneratedToken(res.token)
    this.router.navigateByUrl('board').then(()=>{
      window.location.reload();
    });
    this.setCurrentUser(userData.userId)
    }
    )
}
register(loginData:any){
  return this.http.post<any>('http://localhost:9000/api/v1/registerUser', loginData)
}



}