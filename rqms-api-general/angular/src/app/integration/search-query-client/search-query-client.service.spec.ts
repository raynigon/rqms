import { TestBed } from '@angular/core/testing';

import { SearchQueryClientService } from './search-query-client.service';

describe('SearchQueryClientService', () => {
  let service: SearchQueryClientService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SearchQueryClientService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
