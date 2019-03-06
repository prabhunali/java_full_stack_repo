import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { AdminPaymentSettingComponent } from './admin-payment-setting.component';

describe('AdminPaymentSettingComponent', () => {
  let component: AdminPaymentSettingComponent;
  let fixture: ComponentFixture<AdminPaymentSettingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminPaymentSettingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminPaymentSettingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });


});
