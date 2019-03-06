import { Time } from '@angular/common';
import { typeWithParameters } from '@angular/compiler/src/render3/util';

export class Training {
    id: number;
    userId: number;
    mentorId: number;
    mentorSkillId: number;
    skillId: number;
    status: string;
    progress: number;
    rating: number;
    startDate: string;
    endDate: string;
    startTime: string;
    endTime: string;
    daysOfSession: string;
    trainingTotalHours: number;
    trainingTotalAmount: number;

    constructor(id: number,
                userId: number,
                mentorId: number,
                mentorSkillId: number,
                skillId: number,
                status: string,
                progress: number,
                rating: number,
                startDate: string,
                endDate: string,
                startTime: string,
                endTime: string,
                daysOfSession: string,
                trainingTotalHours: number,
                trainingTotalAmount: number) {
        this.id = id;
        this.userId = userId;
        this.mentorId = mentorId;
        this.mentorSkillId = mentorSkillId;
        this.skillId = skillId;
        this.status = status;
        this.progress = progress;
        this.rating = rating;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.daysOfSession = daysOfSession;
        this.trainingTotalHours = trainingTotalHours;
        this.trainingTotalAmount = trainingTotalAmount;
    }

}