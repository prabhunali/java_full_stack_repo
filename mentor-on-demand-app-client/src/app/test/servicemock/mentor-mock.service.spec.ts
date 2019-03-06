import { TestBed } from '@angular/core/testing';

import { MentorMockService } from './mentor-mock.service';

describe('MentorMockService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: MentorMockService = TestBed.get(MentorMockService);
    expect(service).toBeTruthy();
  });
});
