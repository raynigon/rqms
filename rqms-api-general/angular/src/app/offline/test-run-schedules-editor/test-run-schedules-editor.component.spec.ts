import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TestRunSchedulesEditorComponent } from './test-run-schedules-editor.component';

describe('TestRunSchedulesEditorComponent', () => {
  let component: TestRunSchedulesEditorComponent;
  let fixture: ComponentFixture<TestRunSchedulesEditorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TestRunSchedulesEditorComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TestRunSchedulesEditorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
