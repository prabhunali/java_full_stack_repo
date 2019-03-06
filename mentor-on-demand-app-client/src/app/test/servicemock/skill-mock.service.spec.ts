import { TestBed } from '@angular/core/testing';

import { SkillMockService } from './skill-mock.service';

describe('SkillMockService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SkillMockService = TestBed.get(SkillMockService);
    expect(service).toBeTruthy();
  });
});
