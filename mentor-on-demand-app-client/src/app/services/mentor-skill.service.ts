import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { MentorSkill } from '../models/mentor-skill';
import { ApiURL } from '../utils/ApiURL';
import { ApiResponse } from '../models/api-response';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class MentorSkillService {

  constructor(private http: HttpClient) { }

  saveMentorSkill(mentorSkill: MentorSkill): Observable<MentorSkill[]> {
    const url = ApiURL.MENTOR_SKILLS_SAVE;
    return this.http.post<MentorSkill[]>(url, mentorSkill, httpOptions);
  }

  getMentorSkills(mentorId: number): Observable<MentorSkill[]> {
    const url = ApiURL.MENTOR_SKILLS + `?mentorId=${mentorId}`;
    return this.http.get<MentorSkill[]>(url, httpOptions);
  }

  editMentorSkill(mentorSkill: MentorSkill): Observable<ApiResponse<void>> {
    const url = ApiURL.MENTOR_SKILLS_EDIT;
    return this.http.put<ApiResponse<void>>(url, mentorSkill, httpOptions);
  }

  deleteMentorSkill(id: number): Observable<ApiResponse<void>> {
    // Sample URL: http://localhost:8082/mentorskill-api/mentor_skills/delete/20
    const url = ApiURL.MENTOR_SKILL_DELETE + `/${id}`;
    window.alert("Delete Mentor Skill: " + url);
    return this.http.delete<ApiResponse<void>>(url, httpOptions);
  }

  /** Helper Methods ********/
  getMentorSkillMapByMentorSkillId(mentorSkills: MentorSkill[]): Map<number, MentorSkill> {
    let map = new Map<number, MentorSkill>();

    if(mentorSkills.length != 0) {
      for(let mentorSkill of mentorSkills) {
        map.set(mentorSkill.skillId, mentorSkill);
      }
    }
    return map;
  }

  getMentorSkillBykillId(skillId: number, mentorSkills: MentorSkill[]): MentorSkill {
    let map = this.getMentorSkillMapByMentorSkillId(mentorSkills);
    return map.get(skillId);
  }
  
}
