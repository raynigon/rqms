import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AssertionTestsOverviewComponent } from './assertion-tests-overview.component';

describe('AssertionTestsOverviewComponent', () => {
  let component: AssertionTestsOverviewComponent;
  let fixture: ComponentFixture<AssertionTestsOverviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AssertionTestsOverviewComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AssertionTestsOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
