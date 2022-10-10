import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JudgmentListEditorComponent } from './judgment-list-editor.component';

describe('JudgmentListEditorComponent', () => {
  let component: JudgmentListEditorComponent;
  let fixture: ComponentFixture<JudgmentListEditorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ JudgmentListEditorComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(JudgmentListEditorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
