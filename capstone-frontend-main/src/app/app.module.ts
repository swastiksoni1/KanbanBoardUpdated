import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from './login/login.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { AuthService } from 'src/utils/auth.service';
import { HttpClientModule } from '@angular/common/http';
import { RegisterComponent } from './register/register.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { MatCardModule } from '@angular/material/card';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { NavigationComponent } from './navigation/navigation.component';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { CreateboardComponent } from './createboard/createboard.component';
import { TeamMemberViewComponent } from './team-member-view/team-member-view.component';
import { TaskViewComponent } from './task-view/task-view.component';
import {MatSelectModule} from '@angular/material/select';
import { DragDropModule } from '@angular/cdk/drag-drop';
import { DialogboxBoardComponent } from './dialogbox-board/dialogbox-board.component';
import { MatDialogModule } from '@angular/material/dialog';
import { DialogboxTeamComponent } from './dialogbox-team/dialogbox-team.component';
import { DialogboxTaskComponent } from './dialogbox-task/dialogbox-task.component';
import { DialogboxTaskUpdateComponent } from './dialogbox-task-update/dialogbox-task-update.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    DashboardComponent,
    NavigationComponent,
    CreateboardComponent,
    TeamMemberViewComponent,
    TaskViewComponent,
    DialogboxBoardComponent,
    DialogboxTeamComponent,
    DialogboxTaskComponent,
    DialogboxTaskUpdateComponent
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatInputModule,
    HttpClientModule,
    MatButtonModule,
    MatFormFieldModule,
    MatCardModule,
    MatGridListModule,
    MatIconModule,
    MatMenuModule,
    MatToolbarModule,
    MatSidenavModule,
    MatListModule,
    MatSelectModule,
    DragDropModule,
    MatDialogModule
  ],
  providers: [AuthService],
  bootstrap: [AppComponent]
})
export class AppModule { }
