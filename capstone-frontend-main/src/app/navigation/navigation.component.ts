import { Component, inject } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';
import { AuthService } from 'src/utils/auth.service';
import { TokenService } from 'src/utils/token.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent {
  private breakpointObserver = inject(BreakpointObserver);

  currentUser: any ;
  notLogin:any;
  constructor(private authservice: AuthService, private tokenService : TokenService , private router : Router ){}

  ngOnInit(): void {
    this.currentUser = this.authservice.getCurrentUser();
    this.notLogin=this.authservice.getCurrentUser();
  }

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );

    

    logout(){
      this.tokenService.clearGeneratedToken();
      this.authservice.clearCurrentUser();
      this.router.navigateByUrl("").then(()=>{
        window.location.reload();
      })
      // window.location.reload();
    }
    isloggedIn(){
      return localStorage.getItem("token");
    }
}


