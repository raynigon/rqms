import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OfflineMetricSelectComponent } from './offline-metric-select.component';

describe('OfflineMetricSelectComponent', () => {
  let component: OfflineMetricSelectComponent;
  let fixture: ComponentFixture<OfflineMetricSelectComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OfflineMetricSelectComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OfflineMetricSelectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
