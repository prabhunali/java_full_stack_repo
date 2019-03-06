import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Training } from '../models/training';
import { ApiResponse } from '../models/api-response';
import { Observable } from 'rxjs';
import { ApiURL } from '../utils/ApiURL';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class TrainingService {

  constructor(private http: HttpClient) { }

  proposeTraining(training: Training): Observable<ApiResponse<void>> {
    const url = ApiURL.TRAINING_PROPOSE;
    window.alert(url);
    return this.http.post<ApiResponse<void>>(url, training, httpOptions);
  }

  confirmTraining(trainingId: number, confirmed: boolean): Observable<ApiResponse<void>> {
    // Sample URL = http://localhost:8082/training-api/training/confirm/43/true
    const url = `${ApiURL.TRAINING_CONFIRM}/${trainingId}/${confirmed}`;
    window.alert(url);
    return this.http.put<ApiResponse<void>>(url, httpOptions);
  }

  finalizeTraining(trainingId: number, finalized: boolean): Observable<ApiResponse<void>> {
    const url = `${ApiURL.TRAINING_FINALIZE}/${trainingId}/${finalized}`;
    return this.http.put<ApiResponse<void>>(url, httpOptions);
  }

  getUserTrainings(userId: number): Observable<Training[]> {
    const url = `${ApiURL.TRAINING_BY_USER}?id=${userId}`;
    return this.http.get<Training[]>(url, httpOptions);
  }

  getMentorTrainings(mentorId: number): Observable<Training[]> {
    // Sample URL: http://localhost:8082/training-api/training/mentor?id=12
    const url = `${ApiURL.TRAINING_BY_MENTOR}?id=${mentorId}`;
    return this.http.get<Training[]>(url, httpOptions);
  }

}
