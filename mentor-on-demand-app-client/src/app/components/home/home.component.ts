import { Component, OnInit } from '@angular/core';
import { MentorService } from 'src/app/services/mentor.service';
import { Router } from '@angular/router';
import { TokenStorageService } from 'src/app/modules/authentication/auth-services/token-storage.service';
import { TimeUtil } from 'src/app/utils/time-util';
import * as $ from 'jquery';
import { StringUtil } from 'src/app/utils/string-util';
import { PageURL } from 'src/app/utils/PageURL';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  info: any;

  // Time Picker Properties--------------
  isMeridian = true;
  searchTimeFrom = new Date();
  searchTimeTo = new Date();
  valid = true;
  //-------------------------------------

  constructor(private token: TokenStorageService,
              private mentorService: MentorService,
              private router: Router) { }

  ngOnInit() {
    this.info = {
      token: this.token.getToken(),
      username: this.token.getUsername(),
      authorities: this.token.getAuthorities()
    };
  }

  onSubmit() {
    // Set Time seconds = 0
    this.searchTimeFrom.setSeconds(0);
    this.searchTimeTo.setSeconds(0);

    let skillName: any = $("#searchedSkill").val(); // Note: Set type to any to avoid "undefined" value

    // Get selected/checked days
    let daysAvailableAsArray: string[] = [];
    for(let x=1; x <= 7; x++) {
      if($("#search-chk-day-" + x).prop("checked")) {
        daysAvailableAsArray.push($("#search-chk-day-"+ x).val());
      }
    }

    let daysOfSession = StringUtil.joinStr(daysAvailableAsArray, ",");

    window.alert(
      "Skill: " + skillName +
      ", startTime: " + this.searchTimeFrom +
      ", end Time: " + this.searchTimeTo +
      ", Days: " + daysOfSession
      );

    // Navigate to Mentor Search Page with Parameters
    this.router.navigate([PageURL.SEARCH_MENTOR], 
                          {queryParams: 
                            { skillName: skillName,
                              startTime: this.searchTimeFrom,
                              endTime: this.searchTimeTo,
                              daysOfSession: daysOfSession
                            }
                          }
                        );
      
  }

  isValid(event: boolean): void {
    this.valid = event;
  }

}
