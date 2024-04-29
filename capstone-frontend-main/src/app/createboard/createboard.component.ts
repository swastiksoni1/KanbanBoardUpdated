import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder , Validators, FormGroup} from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { BaordService } from 'src/utils/baord.service';
import { DialogboxBoardComponent } from '../dialogbox-board/dialogbox-board.component';


@Component({
  selector: 'app-createboard',
  templateUrl: './createboard.component.html',
  styleUrls: ['./createboard.component.css']
})
export class CreateboardComponent implements OnInit {
  boardForm: FormGroup;
  isFormVisible: boolean = false;

  constructor(private dialog: MatDialog, private boards:BaordService, private route : Router, private fb:FormBuilder){

  }
  openDialog(): void {
    const dialogRef = this.dialog.open(DialogboxBoardComponent, {
      width: '400px', // Set the width of the dialog as needed
      height: '300px',
      
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });

  }
  

sendData(kanbanBoardId:any){
//  this.boardStorage.sendData(kanbanBoardId)
this.route.navigate(["board/team/", kanbanBoardId])

console.log("boardId in output",kanbanBoardId);

}
kanbanBoardList:any;

ngOnInit():void{
  
this.boards.getAllBoards().subscribe((res ) => {
this.kanbanBoardList=res;

console.log("hello", this.kanbanBoardList);
})

this.boardForm = this.fb.group({
  kanbanBoardId: [null, Validators.required],
  boardName: [null, Validators.required]
});
}

toggleFormVisibility() {
  this.isFormVisible = !this.isFormVisible;
}

onSubmit() {
  if (this.boardForm.valid) {
    this.boards.saveBoard(this.boardForm.value).subscribe((res)=>{
    console.log(res);
   window.location.reload();
    
    })
    console.log(this.boardForm.value);
  } else {
    console.log('Form is invalid');
  }
}

deleteBoard(kanbanBoardId:any){
  this.boards.deleteBoard(kanbanBoardId).subscribe((res)=>{
    console.log("deleted board", kanbanBoardId);
    window.location.reload();
  })
}

}
