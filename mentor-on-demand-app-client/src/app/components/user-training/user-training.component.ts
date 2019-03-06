import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from 'src/app/modules/authentication/auth-services/token-storage.service';
import { TrainingService } from 'src/app/services/training.service';
import { Training } from 'src/app/models/training';

@Component({
  selector: 'app-user-training',
  templateUrl: './user-training.component.html',
  styleUrls: ['./user-training.component.css']
})
export class UserTrainingComponent implements OnInit {

  private userTrainings: Training[];
  private errorMessage = '';

  constructor(private tokenSvc: TokenStorageService, private trainingSvc: TrainingService) { }

  ngOnInit() {

     // Load User Trainings to Page on Load
     this.getUserTrainings(+this.tokenSvc.getId());
  }

  // Load User Trainings to Page
  getUserTrainings(userId: number) {
    this.trainingSvc.getUserTrainings(userId).subscribe(
      data => {
        this.userTrainings = data;
      },
      error => {
        this.errorMessage = error;
        console.log(error);
      }
    );
  }

  finalizeTraining(training: Training) {
    let confirm = window.confirm("Do you really want to finalize this training?");
    if(confirm === true) {
      this.trainingSvc.finalizeTraining(training.id, true).subscribe(
        data => {
          
        },
        error => {
          console.log(error);
        }
      );
      window.alert("Training has been finalized");
      window.location.reload();
      
    } else {
     
    }
  }

  cancelTraining(training: Training) {
    let finalize = window.confirm("Do you really want to cancel this training?");
    if(finalize === true) {
      this.trainingSvc.finalizeTraining(training.id, false).subscribe(
        data => {
        },
        error => {
        
        }
      );
      window.alert("Training has been cancelled");
      window.location.reload();

    } else {
     
    }
  }

}
