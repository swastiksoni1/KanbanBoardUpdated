import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { CreateboardComponent } from './createboard/createboard.component';
import { TeamMemberViewComponent } from './team-member-view/team-member-view.component';
import { TaskViewComponent } from './task-view/task-view.component';

const routes: Routes = [
  {path:"login",component:LoginComponent},
  {path:"register",component:RegisterComponent},
  {path:"",component: DashboardComponent},
  {path:"board",component: CreateboardComponent},
  {path:"board/team/:idb", component:TeamMemberViewComponent},
  {path:"board/team/:idb/:idt", component:TaskViewComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
