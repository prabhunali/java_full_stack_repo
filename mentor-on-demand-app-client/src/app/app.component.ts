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

  // User-entered variables
  searchKeyword = '';
  skillName: any = '';
  dateFromTo: any = '';
  timeFromHidden: any = '';
  timeToHidden: any = '';
  showHideValue = 'Show';

  // Date & Time Picker Variables
  isMeridian = true;
  timeTo = new Date();
  timeFrom = new Date();
  valid = true;

  // Authentication variables
  private loading = false;
  private logStatus = ''; // Login/Logout
  private authority: string;
  private roles: string[];
  private isLoggedIn = false;

  constructor(private token: TokenStorageService, private mentorService: MentorService, private router: Router) {
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

  isValid(event: boolean): void {
    this.valid = event;
  }

  onSubmit() {
    window.alert(TimeUtil.parseTime(this.timeFrom, "en-GB"));
    //this.loading = true;a
    $("#collapsSearchMentor").prop("aria-expanded",false);
    this.skillName = $("#txtSearchKeyWord").val();
    this.dateFromTo = $("#dateFromTo").val();
    this.timeFromHidden = $("#timeFromHidden").val();
    this.timeToHidden = $("#timeToHidden").val();
    this.router.navigate([PageURL.SEARCH_MENTOR], {queryParams: { skillName: this.skillName, dateFromTo: this.dateFromTo, timeFrom: this.timeFromHidden, timeTo: this.timeToHidden }});
    //this.loading = false;
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

  toggleButtonValue() {
    if(this.showHideValue === 'Show') {
      this.showHideValue = 'Hide';
    } else {
      this.showHideValue = 'Show';
    }
  }

}
