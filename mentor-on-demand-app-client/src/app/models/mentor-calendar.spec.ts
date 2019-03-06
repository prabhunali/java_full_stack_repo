import { MentorCalendar } from './mentor-calendar';
import { start } from 'repl';

describe('MentorCalendar', () => {
  it('should create an instance', () => {
    let id: number;
    let mentorSkillId: number;
    let mentorId: number;
    let skillId: number;
    let startTime: string;
    let endTime: string;
    let daysAvailable: string;

    expect(new MentorCalendar(id, mentorSkillId, mentorId, skillId, startTime, endTime, daysAvailable)).toBeTruthy();
  });
});
