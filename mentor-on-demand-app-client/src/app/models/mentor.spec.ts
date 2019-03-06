import { Mentor } from './mentor';

describe('Mentor', () => {
  it('should create an instance', () => {
    let id: number;
    let userId: number;
    let username: string;
    let linkedinUrl: string;
    let yearsOfExperience: number;
    let introduction: string;
    expect(new Mentor(id, userId, username, linkedinUrl, yearsOfExperience, introduction)).toBeTruthy();
  });
});
