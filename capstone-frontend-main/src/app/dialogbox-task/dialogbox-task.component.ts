import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { TaskService } from 'src/utils/task.service';

@Component({
  selector: 'app-dialogbox-task',
  templateUrl: './dialogbox-task.component.html',
  styleUrls: ['./dialogbox-task.component.css']
})
export class DialogboxTaskComponent implements OnInit{
taskForm: FormGroup;
boardId: any;
teamMemberId: any;
constructor(@Inject(MAT_DIALOG_DATA) public datap: any, private fb:FormBuilder, private taskService: TaskService){}
  ngOnInit(): void {
    this.taskForm=this.fb.group({
      taskId:['',Validators.required],
      taskName:['',Validators.required],
      taskDescription:['',Validators.required],
      priority:['',Validators.required]
    })
  }

  onSubmit() {
    if (this.taskForm.valid) {
      this.taskService.saveUserTaskToList(this.datap.boardId,this.datap.teamMemberId, this.taskForm.value).subscribe((res)=>{
        console.log(res);
       window.location.reload();
      });
    } else {
      console.log('Form is invalid');
    }
  }
}


