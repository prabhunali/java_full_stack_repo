import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { MentorSearchResult } from '../models/mentor-search-result';
import { ApiURL } from '../utils/ApiURL';
import { Mentor } from '../models/mentor';
import { ApiResponse } from '../models/api-response';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class MentorService {

  private mentorSearchResults: Observable<MentorSearchResult[]>;
  //private mentorSearchresultsMap: Observable<Map<number, MentorSearchResult[]>>;

  constructor(private http: HttpClient) { }
  
  searchMentors(skillName: string, startTime: any, endTime: any, daysOfSession: string): Observable<MentorSearchResult[]> {
    // Sample URL: http://localhost:8082/mentorskill-api/home/searchmentor?skillName=Java&startTime=09:00:00&endTime=11:00:00&daysOfSession=Fri
    const url = ApiURL.HOME_SEARCH_MENTOR + `?skillName=${skillName}&startTime=${startTime}&endTime=${endTime}&daysOfSession=${daysOfSession}`;
    window.alert(url);
    this.mentorSearchResults = this.http.get<MentorSearchResult[]>(url, httpOptions);
    return this.mentorSearchResults;
  }

  searchMentorProfile(mentorSkillId: number): Observable<MentorSearchResult> {
    const url = ApiURL.HOME_SEARCH_MENTOR_PROFILE + `?mentorSkillId=${mentorSkillId}`;
    return this.http.get<MentorSearchResult>(url, httpOptions);
  }

  getMentorSearchresultsMap() {
    return this.mentorSearchResults;
  }

  getMentor(userId: number): Observable<Mentor> {
    const url = `${ApiURL.MENTOR_GET_BY_USER_ID}?userId=${userId}`;
    return this.http.get<Mentor>(url, httpOptions);
  }

  saveMentor(mentor: Mentor): Observable<Mentor> {
    const url = ApiURL.MENTOR_SAVE;
    return this.http.post<Mentor>(url, mentor, httpOptions);
  }

  updateMentor(mentor: Mentor): Observable<Mentor> {
    const url = ApiURL.MENTOR_EDIT;
    return this.http.put<Mentor>(url, mentor, httpOptions);
  }

}
