import { Component, OnInit } from '@angular/core';
import { MentorService } from 'src/app/services/mentor.service';
import { ActivatedRoute, Router } from '@angular/router';
import { MentorSearchResult } from 'src/app/models/mentor-search-result';
import { PageURL } from 'src/app/utils/PageURL';
import * as $ from "jquery";
import { TimeUtil } from 'src/app/utils/time-util';

@Component({
  selector: 'app-mentor-search',
  templateUrl: './mentor-search.component.html',
  styleUrls: ['./mentor-search.component.css']
})
export class MentorSearchComponent implements OnInit {

  //info: any;
  mentorSearchResults: MentorSearchResult[];

  private startDateTime: Date;
  private endDateTime: Date;
  private daysOfSession = '';
  selfRating = 3.14;

  constructor(private mentorService: MentorService, private router: Router, private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    let skillName = '';
    let dateTimeFromStr = '';
    let dateTimeToStr = '';

    // Get/Parse Query Parameter Values
    this.activatedRoute.queryParams.subscribe(
      params => {
        skillName = params['skillName'];
        dateTimeFromStr = params['startTime'];
        dateTimeToStr = params['endTime'];
        this.daysOfSession = params['daysOfSession'];
      }
    )

    this.startDateTime = new Date(dateTimeFromStr);
    this.endDateTime = new Date(dateTimeToStr);

    this.startDateTime.setSeconds(0);
    this.endDateTime.setSeconds(0);

    // Call search mentor API method
    this.mentorService.searchMentors(skillName
                                   , TimeUtil.parseTime(this.startDateTime, 'en-GB')
                                   , TimeUtil.parseTime(this.endDateTime, 'en-GB')
                                   , this.daysOfSession)
                                   .subscribe(
      data => {
        this.mentorSearchResults = data;
      },
      error => {
        console.log(error);
      }
    );
  }

  viewMentor(mentorSearchResult: MentorSearchResult) {
    //let clickedButtonId = event.srcElement.id;
    //let mentorSkillId = clickedButtonId.split("-")[2];
    this.router.navigate([PageURL.SEARCH_MENTOR_PROFILE], 
                            {queryParams: 
                              {   mentorId: mentorSearchResult.mentor.userId
                                , mentorSkillId: mentorSearchResult.mentorSkill.id
                                , skillId: mentorSearchResult.skill.id
                                , skillName: mentorSearchResult.skill.name
                                , startTime: this.startDateTime
                                , endTime: this.endDateTime
                                , daysOfSession: this.daysOfSession
                              }
                            }
                          );
  }

  getSelfRating(selfRating: number): number {
    this.selfRating = selfRating / 2;
    return selfRating;
  }

}
