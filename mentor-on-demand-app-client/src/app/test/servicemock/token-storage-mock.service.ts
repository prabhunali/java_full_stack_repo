import { Injectable } from '@angular/core';

const TOKEN_KEY       = 'AuthToken';
const USERNAME_KEY    = 'AuthUsername';
const AUTHORITIES_KEY = 'AuthAuthorities';
const ACTIVE_KEY      = 'AuthActive';
const VERIFIED_KEY    = 'AuthVerified';
const ID_KEY          = 'AuthId';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageMockService {

  private roles: Array<string> = [];

  constructor() { }

  signOut() {
    window.sessionStorage.clear();
  }

  public saveToken(token: string) {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);
  }

  public getToken(): string {
    return sessionStorage.getItem(TOKEN_KEY);
  }

  public saveUsername(username: string) {
    window.sessionStorage.removeItem(USERNAME_KEY);
    window.sessionStorage.setItem(USERNAME_KEY, username);
  }

  public getUsername(): string {
    return sessionStorage.getItem(USERNAME_KEY)
  }

  public saveAuthorities(authorities: string[]) {
    window.sessionStorage.removeItem(AUTHORITIES_KEY);
    window.sessionStorage.setItem(AUTHORITIES_KEY, JSON.stringify(authorities));
  }

  public getAuthorities(): string[] {
    this.roles = [];
 
    if (sessionStorage.getItem(TOKEN_KEY)) {
      JSON.parse(sessionStorage.getItem(AUTHORITIES_KEY)).forEach((authority: { authority: string; }) => {
        this.roles.push(authority.authority);
      });
    }
 
    return this.roles;
  }

  public setActive(active: boolean) {
    window.sessionStorage.removeItem(ACTIVE_KEY);
    window.sessionStorage.setItem(ACTIVE_KEY, JSON.stringify(active));
  }

  public isActive(): string {
    return sessionStorage.getItem(ACTIVE_KEY);
  }

  public setVerified(verified: boolean) {
    window.sessionStorage.removeItem(VERIFIED_KEY);
    window.sessionStorage.setItem(VERIFIED_KEY, JSON.stringify(verified));
  }

  public isVerified(): string {
    return sessionStorage.getItem(VERIFIED_KEY);
  }

  public setId(id: number) {
    window.sessionStorage.removeItem(ID_KEY);
    window.sessionStorage.setItem(ID_KEY, JSON.stringify(id));
  }

  public getId(): string {
    return sessionStorage.getItem(ID_KEY);
  }

  public isLoggedIn(): boolean {
    if(this.getToken()) {
      return true;
    }
    return false;
  }

}
