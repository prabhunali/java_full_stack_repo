import { TestBed } from '@angular/core/testing';

import { TokenStorageMockService } from './token-storage-mock.service';

describe('TokenStorageMockService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TokenStorageMockService = TestBed.get(TokenStorageMockService);
    expect(service).toBeTruthy();
  });
});
