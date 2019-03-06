import { TestBed } from '@angular/core/testing';

import { AdminService } from './admin.service';
import { HttpClientTestingModule,
  HttpTestingController } from '@angular/common/http/testing';
import { AdminSetting } from '../models/admin-setting';
import { ApiURL } from '../utils/ApiURL';
import { HttpClient } from '@angular/common/http';
import { TokenStorageService } from '../modules/authentication/auth-services/token-storage.service';

// We declare the variables that we'll use for the Test Controller and for our Service
let httpTestingController: HttpTestingController;
let service: AdminService;

describe('AdminService Testing', () => {  
  beforeEach(() => {
     TestBed.configureTestingModule({
       providers: [AdminService],
       imports: [HttpClientTestingModule]
     });

      // We inject our service (which imports the HttpClient) and the Test Controller
    service =  TestBed.get(AdminService); 
    httpTestingController = TestBed.get(HttpTestingController);
  });

  afterEach(() => {
    httpTestingController.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

});

describe('AdminService.getAdminSettings()', () => {
  it('Returned Observable should match the right data', () => {

      // Create Mock List of Admin Settings
    let setting1: AdminSetting = new AdminSetting(1, "Admin Setting 1 Name", "Admin Setting 1 desc");
    let setting2: AdminSetting = new AdminSetting(2, "Admin Setting 2 Name", "Admin Setting 2 desc");
    let mockSettings: AdminSetting[] = new Array();
    mockSettings.push(setting1, setting2);

    service.getAdminSettings().subscribe(
      settings => {
        expect(settings.length).toBe(2);
        expect(settings[0].id).toEqual(1);
        expect(settings[0].name).toEqual('Admin Setting 1 Name');
        expect(settings[0].description).toEqual('Admin Setting 1 desc');
        expect(settings[1].id).toEqual(2);
        expect(settings[1].name).toEqual('Admin Setting 2 Name');
        expect(settings[1].description).toEqual('Admin Setting 2 desc');

        //expect(data.name).toBe('Luke Skywalker');
      }
    );

    const req = httpTestingController.expectOne(ApiURL.ADMIN_SETTINGS_GET_ALL);
    expect(req.request.url).toBe(ApiURL.ADMIN_SETTINGS_GET_ALL);
    expect(req.request.method).toEqual('GET');
    //expect(req.request.params).toEqual(dummyParams);

    req.flush(mockSettings);

    // req.flush({
    //   incomplete_results: false,
    //   items: [{}, {}],
    //   total_count: 2
    // });

    httpTestingController.verify();
  });
});

describe('AdminService.getAdminSettingById()', () => {
  it('#getAdminSettingById() -> Returned Observable should match the right data', () => {
    const setting = new AdminSetting(1, "setting name", "setting desc");

    service.getAdminSettingById(1).subscribe(
      response => {
        expect(setting.id).toEqual(1);
        expect(setting.name).toEqual("setting name");
        expect(setting.description).toEqual("setting desc");
      }
    );

    const req = httpTestingController.expectOne(`${ApiURL.ADMIN_SETTINGS_GET_BY_ID}/1`);
    expect(req.request.url).toBe(`${ApiURL.ADMIN_SETTINGS_GET_BY_ID }/1`);
    expect(req.request.method).toEqual('GET');
    //expect(req.request.params).toEqual(dummyParams);

    req.flush(setting);

    httpTestingController.verify();
  });
});

describe('AdminService.getAdminSettingByKey()', () => {

  it('#getAdminSettingByKey -> Returned Observable<AdminSetting> should match the right data', () => {
    const mockSetting = new AdminSetting(1, "setting name", "setting desc");

    service.getAdminSettingByKey(1).subscribe(
      actual => {
        expect(actual.id).toEqual(1);
        expect(actual.name).toEqual("setting name");
        expect(actual.description).toEqual("setting desc");
      }
    );

    const url = `${ApiURL.ADMIN_SETTINGS_GET_BY_NAME }/1`;
    const req = httpTestingController.expectOne(url);
    expect(req.request.url).toBe(url);
    expect(req.request.method).toEqual('GET');

    req.flush(mockSetting);

    httpTestingController.verify();
  });
});

describe('AdminService.addAdminSetting() -> Returned Observable<AdminSetting> should match the right data', () => {
  it('#addAdminSetting() -> ', () => {
    const expected = new AdminSetting(1, "setting name", "setting desc");
    const url = ApiURL.ADMIN_SETTINGS_ADD;

    service.addAdminSetting(expected).subscribe(
      actual => {
        expect(actual.id).toEqual(expected.id);
        expect(actual.name).toEqual(expected.name);
        expect(actual.description).toEqual(expected.description);
      }
    );

    const req = httpTestingController.expectOne(url);
    expect(req.request.url).toBe(url);
    expect(req.request.method).toEqual('POST');
    req.flush(expected);
    httpTestingController.verify();
  });

});

describe('AdminService.editAdminSetting()', () => {
   it('#editAdminSetting() -> Returned Observable<AdminSetting> should match the right data', () => {
    const expected = new AdminSetting(1, "setting name", "setting desc");
    const url = ApiURL.ADMIN_SETTINGS_EDIT;

    service.editAdminSetting(expected).subscribe(
      actual => {
        expect(actual.id).toEqual(expected.id);
        expect(actual.name).toEqual(expected.name);
        expect(actual.description).toEqual(expected.description);
      }
    );

    const req = httpTestingController.expectOne(url);
    expect(req.request.url).toBe(url);
    expect(req.request.method).toEqual('PUT');
    req.flush(expected);
    httpTestingController.verify();
  });
});

describe('AdminService.deleteAdminSetting()', () => {
 
  it('#deleteAdminSetting() -> It should delete the correct data', () => {
    const url = `${ApiURL.ADMIN_SETTINGS_DELETE}?id=1`;

    service.deleteAdminSetting(1).subscribe(
      data => {
        expect(data).toBe(1);
      }
    );

    const req = httpTestingController.expectOne(url);
    expect(req.request.method).toBe('DELETE');

    req.flush(1);
    httpTestingController.verify();
  });
});