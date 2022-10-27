import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchQuerySelectComponent } from './search-query-select.component';

describe('SearchQuerySelectComponent', () => {
  let component: SearchQuerySelectComponent;
  let fixture: ComponentFixture<SearchQuerySelectComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SearchQuerySelectComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SearchQuerySelectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
