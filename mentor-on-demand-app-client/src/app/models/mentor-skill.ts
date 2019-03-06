export class MentorSkill {
    
    id: number;
    mentorId: number;
    skillId: number;
    selfRating: number;
    yearOfExperience: number;
    facilitiesOffered: string;
    hourlyRate: number;

    constructor(id: number
            ,   mentorId: number
            ,   skillId: number
            ,   selfRating: number
            ,   yearOfExperience: number
            ,   facilitiesOffered: string
            ,   hourlyRate: number) {

                this.id = id;
                this.mentorId = mentorId;
                this.skillId = skillId;
                this.selfRating = selfRating;
                this.yearOfExperience = yearOfExperience;
                this.facilitiesOffered = facilitiesOffered;
                this.hourlyRate = hourlyRate;
        }

    get Id(): number {
        return this.id;
    }

    set Id(id: number) {
        this.id = id;
    }

    get MentorId(): number {
        return this.mentorId;
    }

    set MentorId(mentorId: number) {
        this.mentorId = mentorId;
    }

    get SkillId(): number {
        return this.skillId;
    }

    set SkillId(skillId: number) {
        this.skillId = skillId;
    }

    get SelfRating(): number {
        return this.selfRating;
    }

    set SelfRating(selfRating: number) {
        this.selfRating = selfRating;
    }

    get YearOfExperience(): number {
        return this.yearOfExperience;
    }

    set YearOfExperience(yearOfExperience: number) {
        this.yearOfExperience = yearOfExperience;
    }

    get FacilitiesOffered(): string {
        return this.facilitiesOffered;
    }

    set FacilitiesOffered(facilitiesOffered: string) {
        this.facilitiesOffered = facilitiesOffered;
    }

    get HourlyRate(): number {
        return this.hourlyRate;
    }

    set HourlyRate(hourlyRate: number) {
        this.hourlyRate = hourlyRate;
    }

}
