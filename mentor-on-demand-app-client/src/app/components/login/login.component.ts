import { Component, OnInit } from '@angular/core';
import { LoginUser } from 'src/app/models/login-user';
import { Router } from '@angular/router';
import { TokenStorageService } from 'src/app/modules/authentication/auth-services/token-storage.service';
import { AuthService } from 'src/app/modules/authentication/auth-services/auth.service';
import { Subscription } from 'rxjs';
import { PageURL } from 'src/app/utils/PageURL';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  form: any = {}
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];
  loginUser: LoginUser;
  subscription: Subscription;
  loading = false;

  constructor(private authService: AuthService, private tokenStorage: TokenStorageService, private router: Router) {
    // redirect to home if already logged in
    if(tokenStorage.isLoggedIn() === true) {
      this.isLoggedIn = true;
      this.router.navigate([PageURL.HOME]);
    }
  }

  ngOnInit() {
    // redirect to home if already logged in
    // if(this.isLoggedIn) {
    //   this.router.navigate([PageURL.HOME]);
    // }

    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getAuthorities();
    }
  }

  onSubmit() {
    console.log(this.form);
 
    this.loginUser = new LoginUser(this.form.username, this.form.password);

    this.loading = true;
    this.authService.login(this.loginUser).subscribe(
      data => {
        this.tokenStorage.saveToken(data.token);
        this.tokenStorage.saveUsername(data.username);
        this.tokenStorage.saveAuthorities(data.authorities);
        this.tokenStorage.setActive(data.active);
        this.tokenStorage.setVerified(data.verified);
        this.tokenStorage.setId(data.id);

        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.roles = this.tokenStorage.getAuthorities();
        this.reloadPage();
        this.router.navigate([PageURL.HOME]);
      },
      error => {
        console.log(error);
        this.errorMessage = error.error.message;
        this.isLoginFailed = true;
        this.loading = false;
      }
    );
  }

  reloadPage() {
    window.location.reload();
  }

}
