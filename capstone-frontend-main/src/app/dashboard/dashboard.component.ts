import { Component, inject, OnInit } from '@angular/core';
import { Breakpoints, BreakpointObserver } from '@angular/cdk/layout';
import { map } from 'rxjs/operators';
import { AuthService } from 'src/utils/auth.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
   // Declare the property here
   existingUser: boolean = false;
  currentUser: any ;
   // Other component properties and methods
 constructor(private authService: AuthService){}

 
 
  ngOnInit(): void {
    this.currentUser = this.authService.getCurrentUser();
    console.log(this.currentUser);
    
  }

  /** Based on the screen size, switch from standard to one column per row */
 
}
