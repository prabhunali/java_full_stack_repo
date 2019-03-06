import { TestBed } from '@angular/core/testing';

import { AuthServiceMockService } from './auth-service-mock.service';

describe('AuthServiceMockService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AuthServiceMockService = TestBed.get(AuthServiceMockService);
    expect(service).toBeTruthy();
  });
});
