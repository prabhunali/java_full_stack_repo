import { Training } from './training';

describe('Training', () => {
  it('should create an instance', () => {
    let id: number;
    let userId: number;
    let mentorId: number;
    let mentorSkillId: number;
    let skillId: number;
    let status: string;
    let progress: number;
    let rating: number;
    let startDate: string;
    let endDate: string;
    let startTime: string;
    let endTime: string;
    let daysOfSession: string;
    let trainingTotalHours: number;
    let trainingTotalAmount: number;

    expect(new Training(
      id,
      userId,
      mentorId,
      mentorSkillId,
      skillId,
      status,
      progress,
      rating,
      startDate,
      endDate,
      startTime,
      endTime,
      daysOfSession,
      trainingTotalHours,
      trainingTotalAmount
    )).toBeTruthy();
  });
});
