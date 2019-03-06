import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/modules/authentication/auth-services/auth.service';
import { Router } from '@angular/router';
import { SignupUser } from 'src/app/models/signup-user';
import { PageURL } from 'src/app/utils/PageURL';

@Component({
  selector: 'app-user-signup',
  templateUrl: './user-signup.component.html',
  styleUrls: ['./user-signup.component.css']
})
export class UserSignupComponent implements OnInit {

  form: any = {};
  signupUserInfo: SignupUser;
  isSignedUp = false;
  isSignUpFailed = false;
  errorMessage = '';
  successMessage = '';
  private loading = false;

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit() {}

  onSubmit() {
    console.log(this.form);

    this.loading = true;

    this.signupUserInfo = new SignupUser(
      this.form.email,
      this.form.password,
      this.form.firstname,
      this.form.lastname,
      this.form.contactnumber,
      'USER'
    )

    this.authService.signup(this.signupUserInfo).subscribe(
      data => {
        console.log(data);
        this.isSignUpFailed = false;
        this.loading = false;
        this.reloadPage();
        this.isSignedUp = true;
        this.successMessage = "Signup successful! Please confirm your email.";
        window.alert("Sign up successful! Please confirm your e-mail to continue logging in.")
        this.router.navigate([PageURL.LOGIN]);
      },
      error => {
        console.log(error);
        window.alert("Sign up Failed!")
        this.router.navigate([PageURL.LOGIN]);
        this.errorMessage = error.error.message;
        this.isSignUpFailed = true;
        this.loading = false;
      }
    )
  }

  reloadPage() {
    window.location.reload();
  }

  // resetFields() {
  //   this.form.firstname = '';
  //   this.form.lastname = '';
  //   this.form.contactnumber = '';
  //   this.form.email = '';
  //   this.form.password = '';
  //   this.form.reenterPassword = '';
  // }

}
