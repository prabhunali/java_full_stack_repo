import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Skill } from '../models/skill';
import { Observable } from 'rxjs';
import { ApiURL } from '../utils/ApiURL';
import { ApiResponse } from '../models/api-response';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class SkillService {

  constructor(private http: HttpClient) { }

  getMentorNonSkills(mentorId: number): Observable<Skill[]> {
    const url = ApiURL.MENTOR_NOT_SKILLS + `?mentorId=${mentorId}`;
    return this.http.get<Skill[]>(url, httpOptions);
  }

  getSkills(): Observable<Skill[]> {
    const url = ApiURL.SKILLS;
    return this.http.get<Skill[]>(url, httpOptions);
  }

  addSkill(skill: Skill): Observable<ApiResponse<void>> {
    const url = ApiURL.ADMIN_SKILL_ADD;
    return this.http.post<ApiResponse<void>>(url, skill, httpOptions);
  }

  editSkill(skill: Skill): Observable<ApiResponse<void>> {
    const url = ApiURL.ADMIN_SKILL_EDIT;
    return this.http.put<ApiResponse<void>>(url, skill, httpOptions);
  }

  deleteSkill(skillId: number): Observable<ApiResponse<void>> {
    const url = ApiURL.ADMIN_SKILL_EDIT + `/${skillId}`;
    return this.http.delete<ApiResponse<void>>(url, httpOptions);
  }

/** Non-API Helper Methods */
  getSkillNames(skills: Skill[]): string[] {
    const skillNames = [];

    if(skills.length > 0) {
      for(let skill of skills) {
        skillNames.push(skill.name);
      }
    }
    return skillNames;
  }

  getSkillMapByKeyId(skills: Skill[]): Map<number, Skill>{
    let map = new Map<number, Skill>();
    
    if(skills.length != 0) {
      for(let skill of skills) {
        map.set(skill.id, skill);
      }
    }
    return map;
  }

  getSkillMapByKeyName(skills: Skill[]): Map<String, Skill> {
    let map = new Map<String, Skill>();

    if(skills.length != 0) {
      for(let skill of skills) {
        map.set(skill.name, skill);
      }
    }

    return map;
  }


}
