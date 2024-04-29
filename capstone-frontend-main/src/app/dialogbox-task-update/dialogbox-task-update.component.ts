import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { TaskService } from 'src/utils/task.service';

@Component({
  selector: 'app-dialogbox-task-update',
  templateUrl: './dialogbox-task-update.component.html',
  styleUrls: ['./dialogbox-task-update.component.css']
})
export class DialogboxTaskUpdateComponent implements OnInit {
  taskForm: FormGroup;
 
  constructor(@Inject(MAT_DIALOG_DATA) public datap: any,private taskService: TaskService, private fb: FormBuilder ){}
ngOnInit(): void {
    this.taskForm=this.fb.group({
    taskName:['',Validators.required],
    taskDescription:['',Validators.required],
    priority:['',Validators.required]
  })

 
  
}
onSubmit(){
  if (this.taskForm.valid) {
    this.taskService.updateUserTask(this.datap.boardId, this.datap.teamMemberId, this.datap.taskId, this.taskForm.value).subscribe((res)=>{
      console.log(res);
      window.location.reload();
    });
  } else {
    console.log('Form is invalid');
  }
}
    
}



