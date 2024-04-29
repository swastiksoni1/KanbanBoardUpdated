import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialog, MatDialogRef } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { TeamMemberService } from 'src/utils/team-member.service';

@Component({
  selector: 'app-dialogbox-team',
  templateUrl: './dialogbox-team.component.html',
  styleUrls: ['./dialogbox-team.component.css']
})
export class DialogboxTeamComponent {
  memberForm: FormGroup;
  boardId:any
  constructor(private activatedRoute: ActivatedRoute,@Inject(MAT_DIALOG_DATA) public datap: any, private dialogRef: MatDialogRef<DialogboxTeamComponent>, private teamService: TeamMemberService, private fb: FormBuilder) {

    this.activatedRoute.params.subscribe((result:any)=>{
      this.boardId = result.idb;
    })
  }

  ngOnInit(): void {
    this.memberForm = this.fb.group({
      memberEmailId: [null, [Validators.required, Validators.email]],
      name: [null, Validators.required]
    });
  }

  onSubmit() {
    if (this.memberForm.valid) {
      this.teamService.saveMember(this.datap.boardId, this.memberForm.value).subscribe((res)=>{
        console.log(res);
        window.location.reload();
      });
    } else {
      console.log('Form is invalid');
    }
  }

}
