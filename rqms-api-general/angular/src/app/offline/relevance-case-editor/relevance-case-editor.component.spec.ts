import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RelevanceCaseEditorComponent } from './relevance-case-editor.component';

describe('JudgmentListEditorComponent', () => {
  let component: RelevanceCaseEditorComponent;
  let fixture: ComponentFixture<RelevanceCaseEditorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RelevanceCaseEditorComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RelevanceCaseEditorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
