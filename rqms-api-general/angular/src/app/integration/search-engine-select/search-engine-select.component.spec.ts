import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchEngineSelectComponent } from './search-engine-select.component';

describe('SearchEngineSelectComponent', () => {
  let component: SearchEngineSelectComponent;
  let fixture: ComponentFixture<SearchEngineSelectComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SearchEngineSelectComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SearchEngineSelectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
