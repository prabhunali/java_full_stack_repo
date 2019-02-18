import { TestBed } from '@angular/core/testing';

import { MentorCalendarService } from './mentor-calendar.service';

describe('MentorCalendarService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: MentorCalendarService = TestBed.get(MentorCalendarService);
    expect(service).toBeTruthy();
  });
});
