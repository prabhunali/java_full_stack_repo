import { MentorSearchResult } from './mentor-search-result';
import { Mentor } from './mentor';
import { Skill } from './skill';
import { MentorSkill } from './mentor-skill';
import { MentorCalendar } from './mentor-calendar';
import { Training } from './training';

describe('MentorSearchResult', () => {
  it('should create an instance', () => {
    let mentor: Mentor;
    let skill: Skill;
    let mentorSkill: MentorSkill;
    let mentorCalendars: MentorCalendar[];
    let mentorFirstName: string;
    let mentorLastName: string;
    let contactNo: string;
    let trainings:  Map<string, Training[]>;

    expect(new MentorSearchResult(mentor, skill, mentorSkill, mentorCalendars, mentorFirstName, mentorLastName, contactNo, trainings)).toBeTruthy();
  });
});
