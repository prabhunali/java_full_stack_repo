import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminSettingsComponent } from './admin-settings.component';
import { AdminService } from 'src/app/services/admin.service';
import { AdminMockService } from 'src/app/test/servicemock/admin-mock.service';

describe('AdminSettingsComponent', () => {
  let component: AdminSettingsComponent;
  let fixture: ComponentFixture<AdminSettingsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminSettingsComponent ],
      providers: [
        {provide: AdminService, useClass: AdminMockService }
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminSettingsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });


});
