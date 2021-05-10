import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TimekeepingIndexComponent } from './timekeeping-index.component';

describe('TimekeepingIndexComponent', () => {
  let component: TimekeepingIndexComponent;
  let fixture: ComponentFixture<TimekeepingIndexComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TimekeepingIndexComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TimekeepingIndexComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
