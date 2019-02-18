import { Component, OnInit, ViewChild } from '@angular/core';
import { TokenStorageService } from 'src/app/modules/authentication/auth-services/token-storage.service';
import { SkillService } from 'src/app/services/skill.service';
import { Skill } from 'src/app/models/skill';
import { Subject, Observable, merge } from 'rxjs';
import {debounceTime, distinctUntilChanged, filter, map, first} from 'rxjs/operators';
import { NgbTypeahead } from '@ng-bootstrap/ng-bootstrap';
import { MentorSkillService } from 'src/app/services/mentor-skill.service';
import { MentorSkill } from 'src/app/models/mentor-skill';
import * as $ from 'jquery';
import { ActionMode } from 'src/app/utils/action-mode.enum';
import { UserService } from 'src/app/services/user.service';
import { User } from 'src/app/models/user';
import { UserProfile } from 'src/app/models/user-profile';
import { MentorCalendar } from 'src/app/models/mentor-calendar';
import { MentorCalendarService } from 'src/app/services/mentor-calendar.service';
import { TimeUtil } from 'src/app/utils/time-util';
import { StringUtil } from 'src/app/utils/string-util';

// External Javascript decalarations
//declare let functionutil: any;
declare function getTableRowIndex(tableId);
//declare let TimeUtil;

@Component({
  selector: 'app-mentor-profile',
  templateUrl: './mentor-profile.component.html',
  styleUrls: ['./mentor-profile.component.css']
})

export class MentorProfileComponent implements OnInit {
  private nonSkills: Skill[];
  private allSkills: Skill[];
  mentorSkills: MentorSkill[];
  private user: User;
  error = '';
  skillsMapById: Map<number, Skill>;
  private crudMode = '';
  mentorCals: MentorCalendar[];

  // Edit Variables
  private mentorSkillToEdit: MentorSkill;
  private mentorCalendarToEdit: MentorCalendar;

  //boolean check
  private isBasicInfoCompleted = false;
  submitted = false;

  // Models
  model: any = {};
  skillsModel: any;
  basicInfo: any = {};

  // Date & Time Picker Variables
  ismeridian = true;
  timeTo: Date = new Date();
  timeFrom: Date = new Date();
  valid = true;

  constructor(private token: TokenStorageService,
              private skillService: SkillService,
              private userService: UserService,
              private mentorSkillService: MentorSkillService,
              private mentorCalService: MentorCalendarService) { }

  ngOnInit() {

    //let testDate = new Date("Mon Feb 18 2019 16:56:37 GMT+0800 (Philippine Standard Time)");
    //window.alert(TimeUtil.parseTime(testDate, ''));

    // Load All Skills Not Owned by Mentor
    this.getMentorNonSkills();

    // Load Mentor Skills
    this.getMentorSkills(+this.token.getId());

    // Load all skills
    this.getAllSkills();

    // Get User details
    this.getUser(+this.token.getId());

    // Get User Profile
    this.getUserProfile(+this.token.getId());

    // Get Mentor Calendars
    this.getMentorCalendars(+this.token.getId());
  }

  private getMentorNonSkills() {
    this.skillService.getMentorNonSkills(+this.token.getId()).subscribe(
      data => {
        this.nonSkills = data;
      }, 
      error => {
        console.log(error);
      }
    );
  }

  private getAllSkills() {
    this.skillService.getSkills().subscribe(
      data => {
        this.allSkills = data;
      }, 
      error => {
        console.log(error);
      }
    );
  }

  private getMentorSkills(mentorId: number) {
    this.mentorSkillService.getMentorSkills(mentorId).subscribe(
      data => {
        this.mentorSkills = data;
      }, 
      error => {
        console.log(error);
      }
    );
  }

  getSkill(skillId: number): Skill {
    return this.skillService.getSkillMapByKeyId(this.allSkills).get(skillId);
  }

  private getUser(userId: number) {
    this.userService.getUser(userId).subscribe(
      data => {
        this.user = data;
        // this.basicInfo.firstName = data.firstName;
        // this.basicInfo.lastName = data.lastName;
        // this.basicInfo.contactNumber = data.contactNumber;
      }, 
      error => {
        console.log(error);
      }
    );
  }

  private getUserProfile(mentorId: number) {
    this.userService.getUserProfile(mentorId).subscribe(
      data => {
        //this.user = data;
        this.basicInfo.firstName = data.firstName;
        this.basicInfo.lastName = data.lastName;
        this.basicInfo.contactNumber = data.contactNumber;
        this.basicInfo.totalYearsExperience = data.totalYearsExp;
        this.basicInfo.contactNumber = data.contactNumber;
        this.basicInfo.linkedInUrl = data.linkedInUrl;
        this.basicInfo.introduction = data.introduction;
      }, 
      error => {
        console.log(error);
      }
    );
  }

  private getMentorCalendars(mentorId: number) {
    this.mentorCalService.getMentorCalendars(mentorId).subscribe(
      data => {
        this.mentorCals = data;
      },
      error => {
        console.log(error);
      }
    );
  }

  getSkillByName(skillName: string): Skill {
    return this.skillService.getSkillMapByKeyName(this.allSkills).get(skillName);
  }

  reloadPage() {
    window.location.reload();
  }

  saveMentorSkill() {
    if(this.crudMode === ActionMode.ADD) {
      this.submitted = true;
      let mentorId = +this.token.getId();
      let skillId = this.skillService.getSkillMapByKeyName(this.allSkills).get(this.model.skillName).id;
      let selfRating = this.model.skillSelfRating;
      let yearOfExperience = this.model.skillYearsOfExp;
      let facilitiesOffered = this.model.skillFacilities;
      let mentorSkill: MentorSkill= new MentorSkill(0
                                                  , mentorId
                                                  , skillId
                                                  , selfRating
                                                  , yearOfExperience
                                                  , facilitiesOffered);

      this.mentorSkillService.saveMentorSkill(mentorSkill).subscribe(
        data => {
          this.reloadPage();
        },
        error => {
          this.error = error;
        }
      );
    }
  }

  editMentorCalendar() {
    let skillName = this.model.calendarSkill;
    let skillId = this.skillService.getSkillMapByKeyName(this.allSkills).get(skillName).id;
    let mentorId = +this.token.getId();
    let mentorSkillId = this.mentorSkillService.getMentorSkillBykillId(skillId, this.mentorSkills).id;
    let timeFromHidden = $("#calTimeFromHidden").val();
    let timeToHidden = $("#calTimeToHidden").val();

    let daysAvailableAsArray: string[] = [];
    for(let x=1; x <= 7; x++) {
      if($("#day-chk-" + x).prop("checked")) {
        daysAvailableAsArray.push($("#day-chk-"+ x).val());
      }
    }
    let daysAvailable = StringUtil.joinStr(daysAvailableAsArray, ",");
    
    let calendar: MentorCalendar = new MentorCalendar(this.mentorCalendarToEdit.id, mentorSkillId, mentorId, skillId, timeFromHidden, timeToHidden, daysAvailable);
    this.mentorCalService.editMentorCalendar(calendar).subscribe(
      data => {
        this.reloadPage();
      },
      error => {
        this.error = error;
      }
    );
  }

  saveMentorCalendar() {
    if(this.crudMode === ActionMode.ADD) {
      let skillName = this.model.calendarSkill;
      let skillId = this.skillService.getSkillMapByKeyName(this.allSkills).get(skillName).id;
      let mentorId = +this.token.getId();
      let mentorSkillId = this.mentorSkillService.getMentorSkillBykillId(skillId, this.mentorSkills).id;
      let timeFromHidden = $("#calTimeFromHidden").val();
      let timeToHidden = $("#calTimeToHidden").val();

      // Check selected availability days
      let daysAvailableAsArray: string[] = [];
      for(let x=1; x <= 7; x++) {
        if(!$("#day-chk-" + x).prop("disabled") && $("#day-chk-" + x).prop("checked")) {
          daysAvailableAsArray.push($("#day-chk-"+ x).val());
        }
      }
      let daysAvailable = StringUtil.joinStr(daysAvailableAsArray, ",");
      
      let calendar: MentorCalendar = new MentorCalendar(0, mentorSkillId, mentorId, skillId, timeFromHidden, timeToHidden, daysAvailable);

      this.mentorCalService.saveMentorCalendar(calendar).subscribe(
        data => {
          this.reloadPage();
        },
        error => {
          this.error = error;
        }
      );
    }
  }

  deleteMentorCalendar(mentorCal: MentorCalendar) {
    this.mentorCalService.deleteMentorCalendar(mentorCal.id).subscribe(
      data => {
        this.reloadPage();
      },
      error => {
        console.log(error);
      }
    );
  }

  // getTableRowIndex(tableId: string) {
  //   let x = getTableRowIndex(tableId);
  //   window.alert(x);
  //   //return rowIndex;
  // }

  loadMentorSkillToEdit(mentorSkill: MentorSkill) {
    this.crudMode = ActionMode.EDIT;
    this.model.skillName = this.getSkill(mentorSkill.skillId).name;
    this.model.skillSelfRating = mentorSkill.selfRating;
    this.model.skillYearsOfExp = mentorSkill.yearOfExperience;
    this.model.skillFacilities = mentorSkill.facilitiesOffered;
    this.mentorSkillToEdit = mentorSkill;
  }

  loadMentorCalendarToEdit(mentorCal: MentorCalendar) {
    this.crudMode = ActionMode.EDIT
    this.model.calendarSkill = this.getSkill(mentorCal.skillId).name;
    //this.timeFrom.setTime(+mentorCal.startTime);
    //this.timeTo.setTime(+mentorCal.startTime);
    this.mentorCalendarToEdit = mentorCal;
  }

  isDayAvailable(cal: MentorCalendar, day: string): boolean {
    return cal.daysAvailableAsArray.includes(day);
  }

  checkDaysAvailable() {
    // Reset checkbox attributes
    for(let x=1; x <= 7; x++) {
      $("#day-chk-"+ x).prop('checked', false);
      $("#day-chk-"+ x).prop("disabled", false);
    }
    let skill = this.getSkillByName(this.model.calendarSkill);
    let calendarMap: Map<number, MentorCalendar[]> = this.mentorCalService.getMentorCalendarsMapByMentorSkillId(this.mentorCals);
    let mentorSkill = this.mentorSkillService.getMentorSkillBykillId(skill.id, this.mentorSkills);
    let calendars: MentorCalendar[] = calendarMap.get(mentorSkill.id);

    for(let calendar of calendars) {
      for(let x=1; x <= 7; x++) {
        let chkDay = $("#day-chk-"+ x).val();
        $("#day-chk-"+ x).prop('checked', calendar.daysAvailableAsArray.includes(chkDay));
        $("#day-chk-"+ x).prop("disabled", calendar.daysAvailableAsArray.includes(chkDay));
      }
    }
  }

  editMentorSkill() {
    //alert("Skill Id: " + this.getSkillByName(this.model.skillName).Id());
    let ms: MentorSkill  = new MentorSkill(this.mentorSkillToEdit.id
                                   , this.mentorSkillToEdit.mentorId
                                   , this.skillService.getSkillMapByKeyName(this.allSkills).get(this.model.skillName).id
                                   , this.model.skillSelfRating
                                   , this.model.skillYearsOfExp
                                   , this.model.skillFacilities);

    // window.alert("Id=" + ms.id + ", Mentor Id="+ ms.mentorId + ", skillId=" + ms.skillId + ", selfRating=" + ms.selfRating + ", years exp="+ ms.yearOfExperience + "facilities=" + ms.facilitiesOffered); 
    this.mentorSkillService.editMentorSkill(ms).subscribe(
      data => {
        window.alert(data.message + " " + data.status);
        this.reloadPage();
      },
      error => {
        this.error = error;
      }
    );  
  }

  deleteMentorSkill(mentorSkill: MentorSkill) {
    this.mentorSkillService.deleteMentorSkill(mentorSkill.id).subscribe(
      data => {
        window.alert(data.message + " " + data.status);
        this.reloadPage();
      },
      error => {
        this.error = error;
      }
    );  
  }

  private updateUserProfile() {
    let id: number = +this.token.getId();
    let firstName = this.basicInfo.firstName;
    let lastName = this.basicInfo.lastName;
    let totalYearsexp = this.basicInfo.totalYearsExperience
    let contactNo = this.basicInfo.contactNumber;
    let linkedInUrl = this.basicInfo.linkedInUrl
    let introduction = this.basicInfo.introduction

    let output = "id="+id+", fname="+firstName+", lname="+lastName+", total exp="+totalYearsexp+" contact="+contactNo+", linkedin="+linkedInUrl+", intro="+ introduction;
    window.alert(output);

    let userProfile = new UserProfile(id, firstName, lastName, totalYearsexp, contactNo, linkedInUrl, introduction);
    this.userService.updateUserProfile(userProfile).subscribe(
      data => {
        this.reloadPage();
      },
      error => {
        console.log(error);
      }
    );
  }

  private changeCrudMode(mode: string) {
    this.crudMode = mode;
  }

  /***** Skill Typehead Start******/
  @ViewChild('instance') instance: NgbTypeahead;
  focus$ = new Subject<string>();
  click$ = new Subject<string>();

  search = (text$: Observable<string>) => {
    const debouncedText$ = text$.pipe(debounceTime(200), distinctUntilChanged());
    const clicksWithClosedPopup$ = this.click$.pipe(filter(() => !this.instance.isPopupOpen()));
    const inputFocus$ = this.focus$;

    return merge(debouncedText$, inputFocus$, clicksWithClosedPopup$).pipe(
      map(term => (term === '' ? this.skillService.getSkillNames(this.nonSkills)
        : this.skillService.getSkillNames(this.nonSkills).filter(v => v.toLowerCase().indexOf(term.toLowerCase()) > -1)).slice(0, 10))
    );
  }
    /****** Skill Typehead end******/

  isValid(event: boolean): void {
    this.valid = event;
  }

  // format = en-US (12-hour with AM/PM); en-GB (24-hour time without AM/PM)
  parseTime(format: string, date: Date) {
    //var d = new Date("Mon Jun 22 03:45:24 PDT 2015")
    var timestamp = date.toLocaleTimeString(format); // timestamp → "03:45:24 AM"

    //var meridian = timestamp.slice(-2);
    // meridian → "AM"

    //window.alert(timestamp + meridian);
  }

  parseMeridian(time: string): string {
    let meridian = time.slice(-2);
    return meridian
  }

}
