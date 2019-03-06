import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginComponent } from './login.component';
import { AuthService } from 'src/app/modules/authentication/auth-services/auth.service';
import { AuthServiceMockService } from 'src/app/test/servicemock/auth-service-mock.service';
import { TokenStorageService } from 'src/app/modules/authentication/auth-services/token-storage.service';
import { TokenStorageMockService } from 'src/app/test/servicemock/token-storage-mock.service';

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LoginComponent ],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });


});
