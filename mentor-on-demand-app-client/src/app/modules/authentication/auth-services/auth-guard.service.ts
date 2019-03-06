import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { TokenStorageService } from './token-storage.service';
import { PageURL } from 'src/app/utils/PageURL';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate{

  constructor(
        private router: Router,
        private authenticationService: AuthService,
        private tokenService: TokenStorageService
  ) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    
    if(this.tokenService.getToken && this.tokenService.isLoggedIn()) {
      // check if route is restricted by role
      //arr1.some(r=> arr2.indexOf(r) >= 0)
      if (route.data.roles && route.data.roles.indexOf(this.tokenService.getAuthorities()[0]) === -1) {
        // role not authorised so redirect to home page
        this.router.navigate(['/home']);
        return false;
      }
      // authorized so return true
      return true;
    }
    // not logged in so redirect to login page with the return url
    this.router.navigate([PageURL.LOGIN], { queryParams: { returnUrl: state.url }});
    return false;
  }

}
