import { Injectable } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';
import { Token } from '@angular/compiler';

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  constructor() { }

  setGeneratedToken(token:string){
    localStorage.setItem('token', token);
  }

  getGeneratedToken(){
    return localStorage.getItem('token');
  }

  clearGeneratedToken(){
    localStorage.removeItem('token');
  }

  getGeneratedHeaders(): HttpHeaders {
    const token = this.getGeneratedToken();
    return new HttpHeaders().set('Authorization', `Bearer ${token}`);
  }

  
}
