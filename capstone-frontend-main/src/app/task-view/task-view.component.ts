import { Component,OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { CdkDragDrop, moveItemInArray, transferArrayItem } from '@angular/cdk/drag-drop';
import { TaskService } from 'src/utils/task.service';
import { DialogboxTaskComponent } from '../dialogbox-task/dialogbox-task.component';
import { MatDialog } from '@angular/material/dialog';
import { DialogboxTaskUpdateComponent } from '../dialogbox-task-update/dialogbox-task-update.component';



@Component({
  selector: 'app-task-view',
  templateUrl: './task-view.component.html',
  styleUrls: ['./task-view.component.css']
})
export class TaskViewComponent implements OnInit {

  constructor(private dialog: MatDialog,private activatedRoute: ActivatedRoute, private taskService:TaskService, private fb:FormBuilder){
    this.activatedRoute.params.subscribe((result:any)=>{
      this.boardId = result.idb;
      this.memberEmailId = result.idt; 
    })
  }

  taskList:any;
   boardId:any
   memberEmailId: any
   taskForm:FormGroup
   taskId:any
   isFormVisible:boolean=false
   isFormVisible1:boolean=false
   draggedTaskId: any;


   openDialogUpdate(taskId:any): void {
    console.log("dialog update ");
    
    const dialogRef = this.dialog.open(DialogboxTaskUpdateComponent, {
      width: '400px', // Set the width of the dialog as needed
      height: '360px',
      data: { boardId: this.boardId ,teamMemberId: this.memberEmailId, taskId: taskId}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      // Perform any necessary actions after the dialog is closed
    });
  }

   openDialog(): void {
    const dialogRef = this.dialog.open(DialogboxTaskComponent, {
      width: '400px', // Set the width of the dialog as needed
      height: '460px',
      data: { boardId: this.boardId ,teamMemberId: this.memberEmailId}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      // Perform any necessary actions after the dialog is closed
    });
  }


  ngOnInit():void{

    this.taskService.getAllTaskFromList(this.boardId, this.memberEmailId).subscribe((res ) => {
      this.taskList=res;
      
      console.log("hello", this.taskList);
      });
}
toggleFormVisibility(taskId:any) {
  this.isFormVisible = !this.isFormVisible;
  this.taskId=taskId
}
toggleFormVisibility1() {
  this.isFormVisible1 = !this.isFormVisible1;
  
}

onSubmit() {
  if (this.taskForm.valid) {
    this.taskService.saveUserTaskToList(this.boardId,this.memberEmailId, this.taskForm.value).subscribe((res)=>{
    console.log(res);
   window.location.reload();
    
    })
    console.log(this.taskForm.value);
  } else {
    console.log('Form is invalid');
  }
}

onUpdate(){
  this.taskService.updateUserTask(this.boardId, this.memberEmailId, this.taskId, this.taskForm.value).subscribe((res)=>{
    console.log(res);
    window.location.reload();
  })
}

deleteTask(taskId:any){
  console.log("delete");
  this.taskService.deleteTaskFromList(this.boardId,this.memberEmailId, taskId ).subscribe((res)=>{
    console.log("delete member email id ", res);
    window.location.reload();
  })
}
setDraggedTaskId(taskId: any) {
  this.draggedTaskId = taskId; // Assign the task.taskId to draggedTaskId
  console.log("on drag"+taskId);
  
}

onDrop(event: CdkDragDrop<any[]>, listName: string, currentTaskId:any): void {

  console.log(currentTaskId);
  
  if (event.previousContainer === event.container) {
    moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
  } else {
    transferArrayItem(
      event.previousContainer.data,
      event.container.data,
      event.previousIndex,
      event.currentIndex
    );
  }

  this.taskService.updateUserTaskStatus(this.boardId, this.memberEmailId, currentTaskId,listName).subscribe((res)=>{
    console.log(res);
    window.location.reload();
  })

  
}

todo = ['Get to work', 'Pick up groceries', 'Go home', 'Fall asleep'];
to_do=[];
  done = ['Get up', 'Brush teeth', 'Take a shower', 'Check e-mail', 'Walk dog'];
  research=[];
  inProgress=[];
  review=[];
  complete=[];
 

}
