import { TestBed, async } from '@angular/core/testing';

import { AuthService } from './auth.service';
import { HttpClient } from '@angular/common/http';
import { AuthToken } from '../auth-models/auth-token';
import { Role } from 'src/app/utils/role.enum';
import { LoginUser } from 'src/app/models/login-user';
import { HttpTestingController, HttpClientTestingModule } from '@angular/common/http/testing';
import { ApiURL } from 'src/app/utils/ApiURL';

// Http Service
let authSvc: AuthService;

// Mock HTTP Client
let httpMock: HttpTestingController;

describe('AuthService Testing', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AuthService],
      imports: [HttpClientTestingModule]
    });

    // We inject our service (which imports the HttpClient) and the Test Controller
    authSvc =  TestBed.get(AuthService); 
    httpMock = TestBed.get(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(authSvc).toBeTruthy();
  });
});

describe('AuthService.login()', () => {
  it('Returned Observable should match the right data', () => {
    let token: AuthToken = new AuthToken();
    token.id = 1;
    token.username = "username";
    token.token = "sadoaansannqk211921212102128sasmasasamasm";
    token.verified = true;
    token.active = true;
    token.authorities = [Role.Mentor];

    let user: LoginUser = new LoginUser("username", "password");

    authSvc.login(user).subscribe(
      response => {
        expect(response.id).toBe(token.id);
        expect(response.username).toBe(token.username);
        expect(response.token).toBe(token.token);
        expect(response.verified).toBe(token.verified);
        expect(response.active).toBe(token.active);
        expect(response.authorities).toBe(token.authorities);
      }
    );

    const url = ApiURL.AUTH_LOGIN;
    const req = httpMock.expectOne(url);
    expect(req.request.url).toBe(url);
    expect(req.request.method).toEqual('POST');
    req.flush(token);
  });
});
