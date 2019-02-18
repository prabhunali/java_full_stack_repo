import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { MentorCalendar } from '../models/mentor-calendar';
import { ApiURL } from '../utils/ApiURL';
import { ApiResponse } from '../models/api-response';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class MentorCalendarService {

  constructor(private http: HttpClient) { }

  saveMentorCalendar(calendar: MentorCalendar): Observable<ApiResponse<void>> {
    const url = ApiURL.MENTOR_CAL_SAVE;
    return this.http.post<ApiResponse<void>>(url, calendar, httpOptions);
  }

  editMentorCalendar(calendar: MentorCalendar): Observable<ApiResponse<void>> {
    const url = ApiURL.MENTOR_CAL_EDIT;
    return this.http.put<ApiResponse<void>>(url, calendar, httpOptions);
  }

  deleteMentorCalendar(id: number): Observable<ApiResponse<void>> {
    const url = ApiURL.MENTOR_CAL_DELETE + `/${id}`;
    return this.http.delete<ApiResponse<void>>(url, httpOptions);
  }

  getMentorCalendars(mentorId: number): Observable<MentorCalendar[]> {
    const url = `${ApiURL.MENTOR_CAL}?mentorId=${mentorId}`;
    return this.http.get<MentorCalendar[]>(url, httpOptions);
  }

  /** Non-API Helper Methods */
  getMentorCalendarsMapByMentorSkillId(cals: MentorCalendar[]) {
    let map = new Map<number, MentorCalendar[]>();
    if(cals.length > 0) {
      let calsArr: Array<MentorCalendar> = new Array<MentorCalendar>();
      for(let cal of cals) {
        if(map.has(cal.mentorSkillId) === false) {
          calsArr =  new Array<MentorCalendar>();
          map.set(cal.mentorSkillId, calsArr);
        }
        calsArr.push(cal);
      }
    }
    return map;
  }



}
