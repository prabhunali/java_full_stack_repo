import { TestBed } from '@angular/core/testing';

import { MentorSkillMockService } from './mentor-skill-mock.service';

describe('MentorSkillMockService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: MentorSkillMockService = TestBed.get(MentorSkillMockService);
    expect(service).toBeTruthy();
  });
});
