import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';
import { AuthToken } from '../auth-models/auth-token';
import { ApiURL } from 'src/app/utils/ApiURL';
import { SignupUser } from 'src/app/models/signup-user';
import { LoginUser } from 'src/app/models/login-user';
import { User } from 'src/app/models/user';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

/**
 * Service responsible for sending Signin/Signup HTTP requests
 */

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) {}

  login(credentials: LoginUser): Observable<AuthToken> {
    const url = ApiURL.AUTH_LOGIN;
    return this.http.post<AuthToken>(url, credentials, httpOptions);
  }

  signup(userSignupInfo: SignupUser): Observable<String> {
    const url = ApiURL.AUTH_SIGNUP;
    return this.http.post<String>(url, userSignupInfo, httpOptions);
  }

  checkIfEmailExists(userEmail: string): Observable<boolean> {
    const url = ApiURL.AUTH_SIGNUP + "/" + userEmail;
    return this.http.get<boolean>(url, httpOptions);
  }

  sendRegistrationCodeMail(userEmail: string): Observable<string> {
    const url = ApiURL.AUTH_SEND_REG_CODE_MAIL;
    return this.http.post<string>(url, userEmail, httpOptions);
  }
}