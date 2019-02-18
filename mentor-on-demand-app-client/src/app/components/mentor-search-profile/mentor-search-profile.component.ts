import { Component, OnInit } from '@angular/core';
import { MentorService } from 'src/app/services/mentor.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-mentor-search-profile',
  templateUrl: './mentor-search-profile.component.html',
  styleUrls: ['./mentor-search-profile.component.css']
})
export class MentorSearchProfileComponent implements OnInit {

  constructor(private mentorService: MentorService, private router: Router, private activatedRoute: ActivatedRoute) { }

  proposeTimeFrom = new Date();
  ismeridian = true;
  valid = true;

  ngOnInit() {
    let mentorSkillId = '';

    // Get/Parse Query Parameter Values
    this.activatedRoute.queryParams.subscribe(
      params => {
        mentorSkillId = params['mentorSkillId'];
      }
    )
  }

  /**Events **/
  isValid(event: boolean): void {
    this.valid = event;
  }

  // formatHourAsMeridian(timeStr: string) {
  //   let h = (d.getHours() + 11) % 12 + 1; //Courtesy @tinka
  //   let m = h > 12 ? 'pm' : 'am';
  // }

  parseTime(date: Date) {
    var d = new Date("Mon Jun 22 03:45:24 PDT 2015")
    // US English uses 12-hour time with AM/PM
    var timestamp = d.toLocaleTimeString('en-US');
      // timestamp → "03:45:24 AM"
    var meridian = timestamp.slice(-2);
      // meridian → "AM"
    window.alert(meridian);
  }

}
