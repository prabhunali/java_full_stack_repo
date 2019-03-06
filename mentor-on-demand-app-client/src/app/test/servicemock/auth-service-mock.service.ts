import { Injectable } from '@angular/core';
import { AuthToken } from 'src/app/modules/authentication/auth-models/auth-token';
import { Observable } from 'rxjs';
import { LoginUser } from 'src/app/models/login-user';
import { Role } from 'src/app/utils/role.enum';
import { SignupUser } from 'src/app/models/signup-user';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceMockService {

  constructor() { }

  login(credentials: LoginUser): Observable<AuthToken> {
    let token: AuthToken = new AuthToken();
    token.id = 1;
    token.username = "username";
    token.active = true;
    token.verified = true;
    token.authorities = [Role.Mentor];

    return new Observable((observer) => {
      observer.next(token);
      observer.complete();
   });
  }

  signup(userSignupInfo: SignupUser): Observable<String> {
   let user: SignupUser = new SignupUser(
     "username",
     "password",
     "first name",
     "last name",
     "contact number",
     "MENTOR"
   );

    return new Observable((observer) => {
      observer.next("Success!");
      observer.complete();
    });
  }
}
