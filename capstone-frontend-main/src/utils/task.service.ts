import { Injectable } from '@angular/core';
import { TokenService } from './token.service';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  constructor(private token:TokenService, private http:HttpClient) { }

  url="http://localhost:9000/api/v1/user/"

  getAllTaskFromList(boardId:string, memberEmailId:string):Observable<any>{
    const Token = this.token.getGeneratedHeaders();
       const options = { headers: Token };
       console.log("id",boardId);
       
       return this.http.get<any>(`${this.url}getAllTask/${boardId}/${memberEmailId}`, options);
  }

  deleteTaskFromList(boardId:string, memberEmailId:string, taskId:string ):Observable<any>{
    const Token = this.token.getGeneratedHeaders();
       const options = { headers: Token };
       return this.http.delete<any>(`${this.url}deleteTask/${boardId}/${memberEmailId}/${taskId}`, options);
  }

  saveUserTaskToList(boardId:string, memberEmailId:any, taskData:any):Observable<any>{
    const Token = this.token.getGeneratedHeaders();
       const options = { headers: Token };
       return this.http.post<any>(`${this.url}saveTask/${boardId}/${memberEmailId}`, taskData ,options);
  }

  updateUserTask(boardId:string, memberEmailId:any, taskId:string, taskData:any ):Observable<any>{
    const Token = this.token.getGeneratedHeaders();
       const options = { headers: Token };
       return this.http.put<any>(`${this.url}updateTask/${boardId}/${memberEmailId}/${taskId}`, taskData, options);
  }

  updateUserTaskStatus(boardId:string,memberEmailId:string,taskId:string,taskStatusValue:any){
    const Token = this.token.getGeneratedHeaders();
       const options = { headers: Token };
       return this.http.put<any>(`${this.url}task/setTaskStatus/${boardId}/${memberEmailId}/${taskId}?taskStatus=${taskStatusValue}`,{}, options);

  }
}
