import { Injectable } from '@angular/core';
import { AdminSetting } from 'src/app/models/admin-setting';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdminMockService {

  constructor() { }

  getAdminSettings(): Observable<AdminSetting[]> {
    let setting1: AdminSetting = new AdminSetting(1, "payment_commision", "Payment comission percentage");
    let setting2: AdminSetting = new AdminSetting(2, "other_setting", "Another setting");

    let settings: AdminSetting[] = new Array();
    settings.push(setting1, setting2);

    // create observable
    return new Observable((observer) => {
       // observable execution
       observer.next(settings);
       observer.complete();
    });

    //   return Observable.create( observer => {
    //     observer.next(settings);
    //     observer.complete();
    // });
  }

  getAdminSettingById(id: number): Observable<AdminSetting> {
    let setting: AdminSetting = new AdminSetting(1, "payment_commision", "Payment comission percentage");
    return new Observable((observer) => {
      // observable execution
      observer.next(setting);
      observer.complete();
    });
  }

  getAdminSettingByKey(key: number): Observable<AdminSetting> {
    let setting: AdminSetting = new AdminSetting(1, "payment_commision", "Payment comission percentage");
    return new Observable((observer) => {
      // observable execution
      observer.next(setting);
      observer.complete();
    });
  }

  addAdminSetting(setting: AdminSetting): Observable<AdminSetting> {
    return new Observable((observer) => {
      // observable execution
      observer.next(setting);
      observer.complete();
    });
  }

  editAdminSetting(setting: AdminSetting): Observable<AdminSetting> {
    return new Observable((observer) => {
      // observable execution
      observer.next(setting);
      observer.complete();
    });
  }

  deleteAdminSetting(id: number) {
   // Void
  }

}
