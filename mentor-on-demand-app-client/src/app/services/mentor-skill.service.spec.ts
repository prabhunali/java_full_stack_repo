import { TestBed } from '@angular/core/testing';

import { MentorSkillService } from './mentor-skill.service';

describe('MentorSkillService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: MentorSkillService = TestBed.get(MentorSkillService);
    expect(service).toBeTruthy();
  });
});
