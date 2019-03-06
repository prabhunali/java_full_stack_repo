import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { ApiURL } from '../utils/ApiURL';
import { Observable } from 'rxjs';
import { AdminSetting } from '../models/admin-setting';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient) { }

  getAdminSettings(): Observable<AdminSetting[]> {
    const url = ApiURL.ADMIN_SETTINGS_GET_ALL;
    return this.http.get<AdminSetting[]>(url, httpOptions);
  }

  getAdminSettingById(id: number): Observable<AdminSetting> {
    const url = `${ApiURL.ADMIN_SETTINGS_GET_BY_ID }/${id}`;
    return this.http.get<AdminSetting>(url, httpOptions);
  }

  getAdminSettingByKey(key: number): Observable<AdminSetting> {
    const url = `${ApiURL.ADMIN_SETTINGS_GET_BY_NAME }/${key}`;
    return this.http.get<AdminSetting>(url, httpOptions);
  }

  addAdminSetting(setting: AdminSetting): Observable<AdminSetting> {
    const url = ApiURL.ADMIN_SETTINGS_ADD;
    return this.http.post<AdminSetting>(url, setting, httpOptions);
  }

  editAdminSetting(setting: AdminSetting): Observable<AdminSetting> {
    const url = ApiURL.ADMIN_SETTINGS_EDIT;
    return this.http.put<AdminSetting>(url, setting, httpOptions);
  }

  deleteAdminSetting(id: number) {
    const url = `${ApiURL.ADMIN_SETTINGS_DELETE}?id=${id}`;
    return this.http.delete(url, httpOptions);
  }

}
