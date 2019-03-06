import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from 'src/app/modules/authentication/auth-services/token-storage.service';
import { SkillService } from 'src/app/services/skill.service';
import { Skill } from 'src/app/models/skill';
import { User } from 'src/app/models/user';
import { ApiResponse } from 'src/app/models/api-response';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-admin-user-settings',
  templateUrl: './admin-user-settings.component.html',
  styleUrls: ['./admin-user-settings.component.css']
})
export class AdminUserSettingsComponent implements OnInit {

  private skills: Skill[];

  constructor(private tokenSvc: TokenStorageService, private userSvc: UserService) { }

  private users: User[];

  ngOnInit() {
    // Load User
    this.userSvc.getUsers().subscribe(
      data => {
        this.users = data;
      },
      error => {
        window.alert("Failed to retrieve users from db (Error: " + error + ")");
      }
    );
  }

  // Block: param block = true
  // Unblock: param block = false;
  blockUser(user: User, block: boolean) {
    let message = (block === true) ? "block" : "unblock";

    let proceed = window.confirm("Do you really want to " + message + " " + user.firstName + " " + user.lastName + "?");

    if(proceed === true) {
      this.userSvc.blockUser(user.id, block).subscribe(
        data => {
          window.alert("User has been successfully " + message + "ed");
          window.location.reload();
        },
        error => {
          window.alert("Failed to " + message + " user (Error: " + error + ")");
        }
      );
    }
  }

}
