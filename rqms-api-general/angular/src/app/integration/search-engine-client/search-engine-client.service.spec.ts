import {TestBed} from '@angular/core/testing';

import {SearchEngineClientService} from './search-engine-client.service';

describe('SearchEngineClientService', () => {
  let service: SearchEngineClientService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SearchEngineClientService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
