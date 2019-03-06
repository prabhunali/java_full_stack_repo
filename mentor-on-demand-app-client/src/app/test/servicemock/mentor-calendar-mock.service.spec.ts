import { TestBed } from '@angular/core/testing';

import { MentorCalendarMockService } from './mentor-calendar-mock.service';

describe('MentorCalendarMockService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: MentorCalendarMockService = TestBed.get(MentorCalendarMockService);
    expect(service).toBeTruthy();
  });
});
