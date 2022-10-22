import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TestRunsOverviewComponent } from './test-runs-overview.component';

describe('TestRunsOverviewComponent', () => {
  let component: TestRunsOverviewComponent;
  let fixture: ComponentFixture<TestRunsOverviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TestRunsOverviewComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TestRunsOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
