import { TestBed } from '@angular/core/testing';

import { PaymentMockService } from './payment-mock.service';

describe('PaymentMockService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PaymentMockService = TestBed.get(PaymentMockService);
    expect(service).toBeTruthy();
  });
});
