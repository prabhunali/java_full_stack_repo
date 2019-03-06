import { MentorSkill } from './mentor-skill';

describe('MentorSkill', () => {
  it('should create an instance', () => {
    let id: number;
    let mentorId: number;
    let skillId: number;
    let selfRating: number;
    let yearOfExperience: number;
    let facilitiesOffered: string;
    let hourlyRate: number;

   expect(new MentorSkill(id, mentorId, skillId, selfRating, yearOfExperience, facilitiesOffered, hourlyRate)).toBeTruthy();
  });
});
