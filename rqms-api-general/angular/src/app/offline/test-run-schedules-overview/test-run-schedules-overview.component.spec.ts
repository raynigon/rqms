import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TestRunSchedulesOverviewComponent } from './test-run-schedules-overview.component';

describe('TestRunSchedulesOverviewComponent', () => {
  let component: TestRunSchedulesOverviewComponent;
  let fixture: ComponentFixture<TestRunSchedulesOverviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TestRunSchedulesOverviewComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TestRunSchedulesOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
