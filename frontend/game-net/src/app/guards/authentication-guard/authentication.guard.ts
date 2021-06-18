import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthenticationService } from 'src/app/modules/authentication/authentication.service';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationGuard implements CanActivate {

  constructor(
    private authenticationService: AuthenticationService,
    private router: Router
  ) {

  }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {  
      
      if(this.authenticationService.isLogedIn()) {
        
        const expectedRoles = route.data.expectedRoles;
        const roles = this.authenticationService.getUserInfo().roles;
        if(expectedRoles.some(r=> roles.includes(r))) {
          return true;
        }
        else {
          this.router.navigate(['']);
          return false;
        }
      }
      this.router.navigate(['']);
      return false;
    }  
}
