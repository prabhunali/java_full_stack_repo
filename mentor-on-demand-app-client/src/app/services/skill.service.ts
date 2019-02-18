import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Skill } from '../models/skill';
import { Observable } from 'rxjs';
import { ApiURL } from '../utils/ApiURL';

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
