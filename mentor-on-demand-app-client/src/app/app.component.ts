import { Component, OnInit } from '@angular/core';
import { MentorService } from './services/mentor.service';
import { Router } from '@angular/router';
import { PageURL } from './utils/PageURL';
import * as $ from 'jquery';
import { TokenStorageService } from './modules/authentication/auth-services/token-storage.service';
import { Role } from './utils/role.enum';
import { TimeUtil } from 'src/app/utils/time-util';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  // Authentication variables
  private loading = false;
  private logStatus = ''; // Login/Logout
  private authority: string;
  private roles: string[];
  private isLoggedIn = false;

  constructor(private token: TokenStorageService, private router: Router) {
    if(this.token.isLoggedIn() === true) {
      this.isLoggedIn = true;
    }
  }

  ngOnInit() {
    if (this.token.getToken()) {
      this.roles = this.token.getAuthorities();

      this.roles.every(role => {
        if (role === Role.Admin) {
          this.authority = 'admin';
          return false;
        } else if (role === Role.Mentor) {
          this.authority = 'mentor';
          return false;
        } else if(role === Role.User){
          this.authority = 'user';
          return false;
        }

        return false;

      });
    }
  }

  logout() {
    this.token.signOut();
    window.location.reload();
  }

  goToLoginPage() {
    this.router.navigate([PageURL.LOGIN]);
  }

  goToMentorSignupPage() {
    this.router.navigate([PageURL.MENTOR_SIGNUP]);
  }

  goToUserrSignupPage() {
    this.router.navigate([PageURL.USER_SIGNUP]);
  }

  goToMentorProfile() {
    this.router.navigate([PageURL.MENTOR_PROFILE]);
  }

  goToMentorTrainingPage() {
    this.router.navigate([PageURL.MENTOR_TRAINING]);
  }

  goToUserProfile() {
    this.router.navigate([PageURL.USER_PROFILE]);
  }

  goToUserTrainingPage() {
    this.router.navigate([PageURL.USER_TRAINING]);
  }

  // goToAdminTrainingPage() {
  //   this.router.navigate([PageURL.admin]);
  // }

  goToAdminSkillPage() {
    this.router.navigate([PageURL.ADMIN_SETTINGS_SKILL]);
  }

  goToAdminPaymentPage() {
    this.router.navigate([PageURL.ADMIN_SETTINGS_PAYMENT]);
  }

  goToAdminUserPage() {
    this.router.navigate([PageURL.ADMIN_SETTINGS_USER]);
  }

  goToAdminSettingsPage() {
    this.router.navigate([PageURL.ADMIN_SETTINGS]);
  }

}
