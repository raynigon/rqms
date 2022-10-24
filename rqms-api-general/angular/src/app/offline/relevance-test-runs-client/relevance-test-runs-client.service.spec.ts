import {TestBed} from '@angular/core/testing';

import {RelevanceTestRunsClientService} from './relevance-test-runs-client.service';

describe('RelevanceTestRunsClientService', () => {
  let service: RelevanceTestRunsClientService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RelevanceTestRunsClientService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
