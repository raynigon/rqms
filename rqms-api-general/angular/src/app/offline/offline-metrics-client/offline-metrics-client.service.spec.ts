import { TestBed } from '@angular/core/testing';

import { OfflineMetricsClientService } from './offline-metrics-client.service';

describe('OfflineMetricsClientService', () => {
  let service: OfflineMetricsClientService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OfflineMetricsClientService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
