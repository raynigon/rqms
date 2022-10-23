import {TestBed} from '@angular/core/testing';

import {RelevanceCasesClientService} from './relevance-cases-client.service';

describe('RelevanceCasesClientService', () => {
  let service: RelevanceCasesClientService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RelevanceCasesClientService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
