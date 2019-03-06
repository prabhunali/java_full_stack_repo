import { TestBed } from '@angular/core/testing';

import { TrainingMockService } from './training-mock.service';

describe('TrainingMockService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TrainingMockService = TestBed.get(TrainingMockService);
    expect(service).toBeTruthy();
  });
});
