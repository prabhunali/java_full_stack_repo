import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MentorSearchProfileComponent } from './mentor-search-profile.component';

describe('MentorSearchProfileComponent', () => {
  let component: MentorSearchProfileComponent;
  let fixture: ComponentFixture<MentorSearchProfileComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MentorSearchProfileComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MentorSearchProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
