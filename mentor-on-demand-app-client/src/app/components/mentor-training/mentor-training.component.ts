import { Component, OnInit } from '@angular/core';
import { Training } from 'src/app/models/training';
import { TrainingService } from 'src/app/services/training.service';
import { TokenStorageService } from 'src/app/modules/authentication/auth-services/token-storage.service';

@Component({
  selector: 'app-mentor-training',
  templateUrl: './mentor-training.component.html',
  styleUrls: ['./mentor-training.component.css']
})
export class MentorTrainingComponent implements OnInit {

  private mentorTrainings: Training[];
  private errorMessage = '';

  constructor(private tokenSvc: TokenStorageService, private trainingSvc: TrainingService) { }

  ngOnInit() {
    this.getMentorTrainings(+this.tokenSvc.getId());
  }

  getMentorTrainings(mentorId: number) {
    this.trainingSvc.getMentorTrainings(mentorId).subscribe(
      data => {
        this.mentorTrainings = data;
      },
      error => {
        this.errorMessage = error;
        console.log(error);
      }
    );
  }

  confirmTraining(training: Training) {
    let confirm = window.confirm("Do you really want to confirm training proposal?");
    if(confirm === true) {
      this.trainingSvc.confirmTraining(training.id, true).subscribe(
        data => {
          
        },
        error => {
          console.log(error);
        }
      );
      window.alert("Training has been confirmed");
      window.location.reload();
      
    } else {
     
    }
  }

  rejectTraining(training: Training) {
    let confirm = window.confirm("Do you really want to reject training proposal?");
    if(confirm === true) {
      this.trainingSvc.confirmTraining(training.id, false).subscribe(
        data => {
          
        },
        error => {
          console.log(error);
        }
      );
      window.alert("Training has been rejected.");
      window.location.reload();
      
    } else {
     
    }
  }

}
