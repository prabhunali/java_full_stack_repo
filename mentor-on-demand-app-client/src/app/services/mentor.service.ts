import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { MentorSearchResult } from '../models/mentor-search-result';
import { ApiURL } from '../utils/ApiURL';

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

  // searchMentor0(skillName: any, dateFromTo: any, timeFrom: any, timeTo: any): Observable<MentorSearchResult[]> {
  //   const url = ApiURL.HOME_SEARCH_MENTOR + `?skillName=${skillName}&dateFromTo=${dateFromTo}&startTime=${timeFrom}&endTime=${timeTo}`;
  //   this.mentorSearchResults =  this.http.get<MentorSearchResult[]>(url, httpOptions);
  //   return this.mentorSearchResults;
  // }

  // getMentorSearchResults() {
  //   return this.mentorSearchResults;
  // }
  
  searchMentors(skillName: any, dateFromTo: any, timeFrom: any, timeTo: any): Observable<MentorSearchResult[]> {
    const url = ApiURL.HOME_SEARCH_MENTOR + `?skillName=${skillName}&dateFromTo=${dateFromTo}&startTime=${timeFrom}&endTime=${timeTo}`;
    window.alert(url);
    this.mentorSearchResults = this.http.get<MentorSearchResult[]>(url, httpOptions);
    return this.mentorSearchResults;
  }

  getMentorSearchresultsMap() {
    return this.mentorSearchResults;
  }

}
