import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminSkillSettingComponent } from './admin-skill-setting.component';

describe('AdminSkillSettingComponent', () => {
  let component: AdminSkillSettingComponent;
  let fixture: ComponentFixture<AdminSkillSettingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminSkillSettingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminSkillSettingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
