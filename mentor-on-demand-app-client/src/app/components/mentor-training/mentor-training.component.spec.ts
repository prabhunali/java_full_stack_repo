import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MentorTrainingComponent } from './mentor-training.component';

describe('MentorTrainingComponent', () => {
  let component: MentorTrainingComponent;
  let fixture: ComponentFixture<MentorTrainingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MentorTrainingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MentorTrainingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
