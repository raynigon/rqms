import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RelevanceCasesOverviewComponent } from './relevance-cases-overview.component';

describe('JudgmentListsOverviewComponent', () => {
  let component: RelevanceCasesOverviewComponent;
  let fixture: ComponentFixture<RelevanceCasesOverviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RelevanceCasesOverviewComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RelevanceCasesOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
