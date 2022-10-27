import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LabelFilterEditorComponent } from './label-filter-editor.component';

describe('LabelFilterEditorComponent', () => {
  let component: LabelFilterEditorComponent;
  let fixture: ComponentFixture<LabelFilterEditorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LabelFilterEditorComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LabelFilterEditorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
