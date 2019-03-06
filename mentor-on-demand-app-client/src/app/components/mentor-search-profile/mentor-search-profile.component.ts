import { Component, OnInit } from '@angular/core';
import { MentorService } from 'src/app/services/mentor.service';
import { Router, ActivatedRoute } from '@angular/router';
import { TokenStorageService } from 'src/app/modules/authentication/auth-services/token-storage.service';
import { TimeUtil } from 'src/app/utils/time-util';
import { Training } from 'src/app/models/training';
import { TrainingStatus } from 'src/app/utils/training-status.enum';
import { Time } from '@angular/common';
import { TrainingService } from 'src/app/services/training.service';
import * as $ from 'jquery';
import { MentorSearchResult } from 'src/app/models/mentor-search-result';
import { map } from 'rxjs/operators';

@Component({
  selector: 'app-mentor-search-profile',
  templateUrl: './mentor-search-profile.component.html',
  styleUrls: ['./mentor-search-profile.component.css']
})
export class MentorSearchProfileComponent implements OnInit {

  private mentorId = '';
  private mentorSkillId = '';
  private skillId = '';
  private skillName = '';
  private dateFromTo = '';
  private dateFrom = '';
  private dateTo = '';
  private timeFromParam = '';
  private timeToParam = '';
  private timeFrom = '';
  private timeTo = '';
  private daysOfSession = '';

  private proposal: any = {};
  private mentorProfile: MentorSearchResult;
  private trainingMap: Map<string, Training[]> = new Map<string, Training[]>();

    // Rating
    averageRating = 0;
    selfRating = 0;


  // Flags
  private submitted: boolean = false;

  constructor(private mentorService: MentorService, private router: Router
            , private activatedRoute: ActivatedRoute
            , private tokenService: TokenStorageService
            , private trainingService: TrainingService) { }

  ngOnInit() {
    // Get/Parse Query Parameter Values
    this.activatedRoute.queryParams.subscribe(
      params => {
        this.mentorId = params['mentorId'];
        this.mentorSkillId = params['mentorSkillId'];
        this.skillId = params['skillId'];
        this.skillName = params['skillName'];
        this.timeFromParam = params['startTime'];
        this.timeToParam = params['endTime'];
        this.daysOfSession = params['daysOfSession'];
      }
    )

    let timeFromAsDate = new Date(this.timeFromParam);
    let timeToAsDate = new Date(this.timeToParam);

    // Reset seconds to 0
    timeFromAsDate.setSeconds(0);
    timeToAsDate.setSeconds(0);

    // Format Time as SQL compatible (24-hour format)
    this.timeFrom = TimeUtil.parseTime(timeFromAsDate, 'en-GB');
    this.timeTo = TimeUtil.parseTime(timeToAsDate, 'en-GB');

    // Bind data to html
    this.proposal.skillName = this.skillName;
    this.proposal.timeFrom = TimeUtil.parseTime(timeFromAsDate, '');
    this.proposal.timeTo = TimeUtil.parseTime(timeToAsDate, '');

    // Retrieve Mentor Profile Details
    this.onPageLoad();

    // window.alert(this.mentorProfile.trainings.size);
    // for (let entry of Array.from(this.mentorProfile.trainings.entries())) {
    //   let key = entry[0];
    //   let value = entry[1];

    //   window.alert(key);
    // }
    this.averageRating = this.computeAverageRating();

  }

  onSubmit() {
    this.submitted = true;

    if(this.tokenService.isLoggedIn()) {
      this.dateFromTo = $("#proposeDateFromTo").val();
      let dateFromToArr: string[] = this.dateFromTo.split(" - ");
      this.dateFrom = TimeUtil.formatDateAsSQLDate(dateFromToArr[0], "/");
      this.dateTo = TimeUtil.formatDateAsSQLDate(dateFromToArr[1], "/");
     
      // window.alert(
      //   "userId: " + this.tokenService.getId() +
      //   "mentorId: " + this.mentorId +
      //   " | mentorSkillId: " + this.mentorSkillId +
      //   " | skillId: " + this.skillId +
      //   " | skillName: " + this.skillName +
      //   " | dateFrom: " + this.dateFrom +
      //   " | dateTo: " + this.dateTo +
      //   " | timeFrom: " + this.timeFrom +
      //   " | timeTo: " + this.timeTo +
      //   " | Message: " + this.proposal.message
      // );

      let training: Training = new Training(
        null,
        +this.tokenService.getId(),
        +this.mentorId,
        +this.mentorSkillId,
        +this.skillId,
        TrainingStatus.PROPOSED,
        0.00,
        0,
        this.dateFrom,
        this.dateTo,
        this.timeFrom,
        this.timeTo,
        this.daysOfSession,
        0,                                        // Initial value is 0, calculate this in the backend
        this.mentorProfile.mentorSkill.hourlyRate // Initial value is mentor's hourly rate; re-calculate it in the backend (hourlyRate * totalTrainingHours)
      );

      this.trainingService.proposeTraining(training).subscribe(
        data => {
          window.alert("Training proposal was successfully sent to mentor. Please wait for mentor's response.");
          
          this.submitted = true;

          // Reset Fields
          this.proposal.skillName = '';
          this.proposal.timeFrom = '';
          this.proposal.timeTo = '';
          this.proposal.dateFromTo = '';
        },
        error => {
          window.alert("Training proposal was successfully sent to mentor. Please wait for mentor's response.");
          console.log(error);
        }
      );
    } else {
      window.alert("Please login to continue sending proposal.")
    }

    this.submitted = false;
  }

  onPageLoad() {
    // Mentor Complete name,  Ave. rating, self-rating, years of experience
    this.mentorService.searchMentorProfile(+this.mentorSkillId).subscribe(
      data => {
        this.mentorProfile = data;
        this.trainingMap = data.trainings;
        this.selfRating = data.mentorSkill.selfRating;
      },
      error => {
        window.alert("Unable to read mentor's data.");
        console.log(error);
      }
    );
  }

  computeAverageRating(): number {
    if(this.trainingMap == null) {return 0;}

    let ratingTotal = 0;
    let trainingCount = 0;

    this.trainingMap.forEach((value: Training[], key: string) => {
      for(let tr of value) {
        // console.log(key, value);
        trainingCount = trainingCount + 1;
        ratingTotal = ratingTotal + ratingTotal;
      }
    });

    return ratingTotal / trainingCount;
  }


}
