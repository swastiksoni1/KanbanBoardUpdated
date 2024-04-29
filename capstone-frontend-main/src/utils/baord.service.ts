import { Injectable } from '@angular/core';
import { TokenService } from './token.service';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class BaordService {

  constructor(private token:TokenService, private http:HttpClient) { }

  url="http://localhost:9000/api/v1/user/"

  getAllBoards():Observable<[]>{
    const Token = this.token.getGeneratedHeaders();
       const options = { headers: Token };
       return this.http.get<[]>(`${this.url}getAllBoards`, options);
  }

  deleteBoard(boardId:string):Observable<any>{
    const Token = this.token.getGeneratedHeaders();
       const options = { headers: Token };
       return this.http.delete<any>(`${this.url}deleteBoard/${boardId}`, options);
  }

  saveBoard(savedBoard:any):Observable<any>{
    const Token = this.token.getGeneratedHeaders();
       const options = { headers: Token };
       return this.http.post<any>(`${this.url}saveBoard`, savedBoard, options);
  }

  // getallboards(): Observable<BOARDS[]> {
  //   const tok = this.token.getHeaders();
  //   const options = { headers: tok };
  //   return this.HTTP.get<BOARDS[]>(`${this.url}getallboards`, options);
  // }

}
