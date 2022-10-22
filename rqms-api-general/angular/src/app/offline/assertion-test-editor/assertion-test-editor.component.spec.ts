import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AssertionTestEditorComponent } from './assertion-test-editor.component';

describe('AssertionTestEditorComponent', () => {
  let component: AssertionTestEditorComponent;
  let fixture: ComponentFixture<AssertionTestEditorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AssertionTestEditorComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AssertionTestEditorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
