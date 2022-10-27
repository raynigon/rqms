import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchQueryEditorComponent } from './search-query-editor.component';

describe('SearchQueryEditorComponent', () => {
  let component: SearchQueryEditorComponent;
  let fixture: ComponentFixture<SearchQueryEditorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SearchQueryEditorComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SearchQueryEditorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
