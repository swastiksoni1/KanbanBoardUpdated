import { Component, OnInit} from '@angular/core';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { BaordService } from 'src/utils/baord.service';


@Component({
  selector: 'app-dialogbox-board',
  templateUrl: './dialogbox-board.component.html',
  styleUrls: ['./dialogbox-board.component.css']
})
export class DialogboxBoardComponent implements OnInit{

  boardForm: FormGroup;
  isFormVisible: boolean = false;

  constructor(private dialogRef: MatDialogRef<DialogboxBoardComponent>, private boards: BaordService, private fb: FormBuilder) {}
 
  ngOnInit(): void {
    this.boardForm = this.fb.group({
      kanbanBoardId: [null, Validators.required],
      boardName: [null, Validators.required]
    });

}

onSubmit() {
  if (this.boardForm.valid) {
    this.boards.saveBoard(this.boardForm.value).subscribe((res) => {
      console.log(res);
      this.dialogRef.close();
      window.location.reload();
    });
    console.log(this.boardForm.value);
  } else {
    console.log('Form is invalid');
  }
}

}
