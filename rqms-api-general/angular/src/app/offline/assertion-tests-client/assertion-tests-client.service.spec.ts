import { TestBed } from '@angular/core/testing';

import { AssertionTestsClientService } from './assertion-tests-client.service';

describe('AssertionTestsClientService', () => {
  let service: AssertionTestsClientService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AssertionTestsClientService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
