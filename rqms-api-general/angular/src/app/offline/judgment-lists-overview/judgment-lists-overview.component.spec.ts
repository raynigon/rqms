import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JudgmentListsOverviewComponent } from './judgment-lists-overview.component';

describe('JudgmentListsOverviewComponent', () => {
  let component: JudgmentListsOverviewComponent;
  let fixture: ComponentFixture<JudgmentListsOverviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ JudgmentListsOverviewComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(JudgmentListsOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
