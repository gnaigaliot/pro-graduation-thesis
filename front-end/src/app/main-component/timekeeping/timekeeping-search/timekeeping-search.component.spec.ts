import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TimekeepingSearchComponent } from './timekeeping-search.component';

describe('TimekeepingSearchComponent', () => {
  let component: TimekeepingSearchComponent;
  let fixture: ComponentFixture<TimekeepingSearchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TimekeepingSearchComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TimekeepingSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
