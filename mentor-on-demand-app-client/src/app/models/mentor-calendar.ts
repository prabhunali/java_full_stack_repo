export class MentorCalendar {

    public id: number;
    public mentorSkillId: number;
    public mentorId: number;
    public skillId: number;
    public startTime: string;
    public endTime: string;
    public daysAvailable: string;
    public daysAvailableAsArray: string[];

    constructor(id: number, mentorSkillId: number, mentorId: number, skillId: number, startTime: string, endTime: string, daysAvailable: string) {
        this.id = id;
        this.mentorSkillId = mentorSkillId;
        this.mentorId = mentorId;
        this.skillId = skillId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.daysAvailable = daysAvailable;
    }

    // getDaysAvailable(): string[] {
    //     return this.daysAvailable.split(",");
    // }

}
