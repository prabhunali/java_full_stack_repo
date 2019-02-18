import { Component, OnInit } from '@angular/core';
import { MentorService } from 'src/app/services/mentor.service';
import { ActivatedRoute, Router } from '@angular/router';
import { MentorSearchResult } from 'src/app/models/mentor-search-result';
import { PageURL } from 'src/app/utils/PageURL';
import * as $ from "jquery";

@Component({
  selector: 'app-mentor-search',
  templateUrl: './mentor-search.component.html',
  styleUrls: ['./mentor-search.component.css']
})
export class MentorSearchComponent implements OnInit {

  //info: any;
  mentorSearchResults: MentorSearchResult[];

  constructor(private mentorService: MentorService, private router: Router, private activatedRoute: ActivatedRoute) { }

  private skillName = '';
  private dateFromTo = '';
  private timeFrom = '';
  private timeTo = '';

  ngOnInit() {
    // Get/Parse Query Parameter Values
    this.activatedRoute.queryParams.subscribe(
      params => {
        this.skillName = params['skillName'];
        this.dateFromTo = params['dateFromTo'];
        this.timeFrom = params['timeFrom'];
        this.timeTo = params['timeTo'];
      }
    )

    // Call search mentor API method
    this.mentorService.searchMentors(this.skillName, this.dateFromTo, this.timeFrom, this.timeTo).subscribe(
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
    this.router.navigate([PageURL.SEARCH_MENTOR_PROFILE], {queryParams: { mentorId: mentorSearchResult.mentor.id
                                                        , mentorSkillId: mentorSearchResult.mentorSkill.id
                                                        , skillName: this.skillName
                                                        , dateFromTo: this.dateFromTo
                                                        , timeFrom: this.timeFrom
                                                        , timeTo: this.timeTo }});
  }

}
