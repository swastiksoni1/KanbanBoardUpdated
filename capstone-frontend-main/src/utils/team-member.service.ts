import { Injectable } from '@angular/core';
import { TokenService } from './token.service';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TeamMemberService {

  constructor(private token:TokenService, private http:HttpClient) { }

  url="http://localhost:9000/api/v1/user/"

  getAllTeamMembers(boardId:string):Observable<[]>{
    const Token = this.token.getGeneratedHeaders();
       const options = { headers: Token };
       console.log("id",boardId);
       
       return this.http.get<[]>(`${this.url}getAllMember/${boardId}`, options);
  }

  saveMember(boardId:string, memberData:any):Observable<any>{
    const Token = this.token.getGeneratedHeaders();
       const options = { headers: Token };
       return this.http.post<any>(`${this.url}saveMember/${boardId}`,memberData, options);
  }

  deleteTeamMember(boardId:string, teamMemberId:string ):Observable<any>{
    const Token = this.token.getGeneratedHeaders();
       const options = { headers: Token };
       return this.http.delete<any>(`${this.url}deleteMember/${boardId}/${teamMemberId}`, options);
  }
}
