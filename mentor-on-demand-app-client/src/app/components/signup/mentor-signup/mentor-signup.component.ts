import { Component, OnInit } from '@angular/core';
import { SignupUser } from 'src/app/models/signup-user';
import { AuthService } from 'src/app/modules/authentication/auth-services/auth.service';
import { Router } from '@angular/router';
import { PageURL } from 'src/app/utils/PageURL';

@Component({
  selector: 'app-mentor-signup',
  templateUrl: './mentor-signup.component.html',
  styleUrls: ['./mentor-signup.component.css']
})
export class MentorSignupComponent implements OnInit {

  form: any = {};
  signupUserInfo: SignupUser;
  isSignedUp = false;
  isSignUpFailed = false;
  errorMessage = '';

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit() {}

  onSubmit() {
    console.log(this.form);
    
    this.signupUserInfo = new SignupUser(
      this.form.email,
      this.form.password,
      this.form.firstname,
      this.form.lastname,
      this.form.contactnumber,
      'Mentor'
    )

    this.authService.signup(this.signupUserInfo).subscribe(
      data => {
        console.log(data);
        this.isSignedUp = true;
        this.isSignUpFailed = false;
        this.router.navigate([PageURL.LOGIN]);
      },
      error => {
        console.log(error);
        this.errorMessage = error.error.message;
        this.isSignUpFailed = true;
      }
    )
  }

}
