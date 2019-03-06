import { Component, OnInit } from '@angular/core';
import { SkillService } from 'src/app/services/skill.service';
import { Skill } from 'src/app/models/skill';
import { TokenStorageService } from 'src/app/modules/authentication/auth-services/token-storage.service';
import { ActionMode } from 'src/app/utils/action-mode.enum';

@Component({
  selector: 'app-admin-skill-setting',
  templateUrl: './admin-skill-setting.component.html',
  styleUrls: ['./admin-skill-setting.component.css']
})
export class AdminSkillSettingComponent implements OnInit {

  private admin: any = {};
  private skills: Skill[];
  private crudMode = '';
  //private isValid: boolean = true;;

  constructor(private tokenSvc: TokenStorageService, private skillSvc: SkillService) { }

  ngOnInit() {
    // Load all skills on page load
    this.skillSvc.getSkills().subscribe(
      data => {
        this.skills = data;
      },
      error => {
        window.alert(error);
      }
    );
  }

  addSkill() {
    let skill: Skill = new Skill(0, this.admin.adminSkillName, this.admin.adminSkillDescription, this.admin.adminSkillPreRequisites);
    this.skillSvc.addSkill(skill).subscribe(
      data => {
        window.alert("New skill has been successfully added.");
        window.location.reload();
      },
      error => {
        window.alert("Failed to add new skill.");
        window.location.reload();
      }
    );
  }

  editSkill() {
    this.crudMode = ActionMode.EDIT;

    if(this.admin.adminSkillId == "" || this.admin.adminSkillName == "" || this.admin.adminSkillDescription == "") {
      //this.isValid = false;
      window.alert("Some of the required fields is empty or invalid.");
      return;
    }

    let skill: Skill = new Skill(
      +this.admin.adminSkillId,
      this.admin.adminSkillName,
      this.admin.adminSkillDescription,
      this.admin.adminSkillPreRequisites
    );

    let confirm = window.confirm("Do you really want to update skill?");
    
    if(confirm === true) {
      this.skillSvc.editSkill(skill).subscribe(
        data => {
          window.alert("Skill has been successfully updated.");
          window.location.reload();
        },
        error => {
          window.alert("Failed to update skill.");
          window.location.reload();
        }
      );
    }
  }

  deleteSkill(skill: Skill) {
    let confirm = window.confirm("Do you really want to delete skill?");
    
    if(confirm === true) {
      this.skillSvc.deleteSkill(skill.id).subscribe(
        data => {
          window.alert("Skill has been successfully deleted.");
          window.location.reload();
        },
        error => {
          window.alert("Failed to delete skill.");
          window.location.reload();
        }
      );
    }
  }

  private changeCrudMode(mode: string) {
    this.crudMode = mode;
  }

  loadSkillToEdit(skill: Skill) {
    this.crudMode = ActionMode.EDIT;
    this.admin.adminSkillId = skill.id;
    this.admin.adminSkillName = skill.name;
    this.admin.adminSkillDescription = skill.description;
    this.admin.adminSkillPreRequisites = skill.prerequisites;
  }

  helloWorld(): string {
    return 'Hello world!';
  }

}
