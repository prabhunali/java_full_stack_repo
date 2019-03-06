import { Component, OnInit } from '@angular/core';
import { AdminService } from 'src/app/services/admin.service';
import { AdminSetting } from 'src/app/models/admin-setting';
import { ActionMode } from 'src/app/utils/action-mode.enum';

@Component({
  selector: 'app-admin-settings',
  templateUrl: './admin-settings.component.html',
  styleUrls: ['./admin-settings.component.css']
})
export class AdminSettingsComponent implements OnInit {

  private adminSetting: any = {};
  private crudMode = '';
  adminSettings: AdminSetting[];


  constructor(private adminSvc: AdminService) { }

  ngOnInit() {
    // Load Admin Settings
    this.adminSvc.getAdminSettings().subscribe(
      data => {
        this.adminSettings = data;
      },
      error => {
        console.log(error);
        window.alert(error);
      }
    );

  }

  loadAdminSettingToEdit(setting: AdminSetting) {
    this.changeCrudMode(ActionMode.EDIT);
    this.adminSetting.id = setting.id;
    this.adminSetting.name = setting.name;
    this.adminSetting.description = setting.description;
  }

  deleteAdminSetting(setting: AdminSetting) {
    let confirm: boolean = window.confirm("Do you really want to delete this setting?");
    if(confirm === true) {
      this.adminSvc.deleteAdminSetting(setting.id).subscribe(
        data => {
          window.alert("Setting has been successfully deleted.");
          window.location.reload();
        },
        error => {
          window.alert("Failed to delete setting.");
        }
      );
    }
  }

  addAdminSetting() {
    //alert(this.adminSetting.name + this.adminSetting.description);
    let confirm = window.confirm("Do you really want to add this setting?");
    if(confirm === true) {
      let setting: AdminSetting = new AdminSetting(null , this.adminSetting.name, this.adminSetting.description);
      this.adminSvc.addAdminSetting(setting).subscribe(
        data => {
          window.alert("Setting has been successfully added.");
          window.location.reload();
        },
        error => {
          window.alert("Failed to add new setting.");
        }
      );
    }
  }

  editAdminSetting() {
    let confirm = window.confirm("Do you really want to edit this setting?");
    if(confirm === true) {
      let setting: AdminSetting = new AdminSetting(this.adminSetting.id, this.adminSetting.name, this.adminSetting.description);

      this.adminSvc.editAdminSetting(setting).subscribe(
        data => {
          window.alert("Setting has been successfully updated.");
          window.location.reload();
        },
        error => {
          window.alert("Failed to save setting updates.");
        }
      );
    }
  }

  private changeCrudMode(mode: string) {
    this.crudMode = mode;
  }

}
