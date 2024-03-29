import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminUserSettingsComponent } from './admin-user-settings.component';

describe('AdminUserSettingsComponent', () => {
  let component: AdminUserSettingsComponent;
  let fixture: ComponentFixture<AdminUserSettingsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminUserSettingsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminUserSettingsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
