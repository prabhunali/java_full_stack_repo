import { UserProfile } from './user-profile';

describe('UserProfile', () => {
  it('should create an instance', () => {
    let id: number;
    let firstName: string;
    let lastName: string;
    let totalYearsExp: number;
    let contactNumber: string;
    let linkedInUrl: string;
    let introduction: string;
    expect(new UserProfile(
      id,
      firstName,
      lastName,
      totalYearsExp,
      contactNumber,
      linkedInUrl,
      introduction
    )).toBeTruthy();
  });
});
