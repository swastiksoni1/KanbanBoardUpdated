import { Component, Inject, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup,Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

import { TeamMemberService } from 'src/utils/team-member.service';
import { DialogboxTeamComponent } from '../dialogbox-team/dialogbox-team.component';
import { MatDialog } from '@angular/material/dialog';



@Component({
  selector: 'app-team-member-view',
  template: `
    <!-- <app-createboard (boardId)="receiveData($event)"></app-createboard> -->
    <!-- <div>Data received in parent component: {{ receivedData }}</div> -->
  `,
  templateUrl: './team-member-view.component.html',
  styleUrls: ['./team-member-view.component.css']
})
export class TeamMemberViewComponent implements OnInit {
 

@Input() data :any;
boardId:any
teamMemberList: any;
isFormVisible:boolean= false;
   
memberForm: FormGroup;
  constructor(private dialog: MatDialog, private formBuilder: FormBuilder,  private teamMember:TeamMemberService, private router: Router , private activatedRoute: ActivatedRoute){
    this.activatedRoute.params.subscribe((result:any)=>{
      this.boardId = result.idb;
    })

   
  }
  openDialog(): void {
    const dialogRef = this.dialog.open(DialogboxTeamComponent, {
      width: '400px', // Set the width of the dialog as needed
      height: '300px',
      data: { boardId: this.boardId }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      // Perform any necessary actions after the dialog is closed
    });
  }

  // recievedData(data:any){
  //   this.boardId= data;
  //   console.log("boardId in input ", this.boardId);
    
  // }
 

sendData( memberEmailId:string){
  this.router.navigate(["board/team/",this.boardId, memberEmailId])
  // this.memberStorage.sendData(memberEmailId)
}

  ngOnInit():void{

    this.teamMember.getAllTeamMembers(this.boardId).subscribe((res:any ) => {
      this.teamMemberList=res;
      
      console.log("hello", this.teamMemberList);
      });

      this.memberForm = this.formBuilder.group({
        memberEmailId: ['', Validators.required],
        name: ['', Validators.required]
      });
      
  }

  toggleFormVisibility() {
    this.isFormVisible = !this.isFormVisible;
  }
  
  onSubmit(){
    console.log("onsubmit");
    
    this.teamMember.saveMember(this.boardId, this.memberForm.value).subscribe((res)=>{
      console.log(res);
      window.location.reload();
    })
   
  }

  deleteMember(memberEmailId:any){
    console.log("delete");
    this.teamMember.deleteTeamMember(this.boardId,memberEmailId ).subscribe((res)=>{
      console.log("delete member email id ", res);
      window.location.reload();
    })

    

  }

}
