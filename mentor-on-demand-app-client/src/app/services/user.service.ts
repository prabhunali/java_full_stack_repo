import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { User } from '../models/user';
import { ApiURL } from '../utils/ApiURL';
import { Observable } from 'rxjs';
import { ApiResponse } from '../models/api-response';
import { UserProfile } from '../models/user-profile';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  getUser(id: number): Observable<User> {
    const url = ApiURL.USER_BY_ID + `/${id}`;
    return this.http.get<User>(url, httpOptions);
  }

  updateUserProfile(userProfile: UserProfile): Observable<ApiResponse<void>> {
    // Sample URL: http://localhost:8082/user-api/users/update
    const url = ApiURL.USER_UPDATE_PROFILE;
    return this.http.put<ApiResponse<void>>(url, userProfile, httpOptions);
  }

  getUserProfile(mentorId: number): Observable<UserProfile> {
    // Sample URL:  http://localhost:8082/user-api/users/profile?mentorId=12
    const url = ApiURL.USER_GET_PROFILE + `?mentorId=${mentorId}`;
    return this.http.get<UserProfile>(url, httpOptions);
  }

  blockUser(userId: number, block: boolean): Observable<ApiResponse<void>> {
    const url = `${ApiURL.ADMIN_BLOCK_USER}/${userId}/${block}`;
    return this.http.put<ApiResponse<void>>(url, httpOptions);
  }

  getUsers(): Observable<User[]> {
    const url = ApiURL.ADMIN_GET_USERS;
    return this.http.get<User[]>(url, httpOptions);
  }

}
