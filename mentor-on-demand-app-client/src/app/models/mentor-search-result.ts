import { MentorSkill } from './mentor-skill';
import { Mentor } from './mentor';
import { Skill } from './skill';
import { MentorCalendar } from './mentor-calendar';

export class MentorSearchResult {
    
    private _mentor: Mentor;
    private _skill: Skill;
    private _mentorSkill: MentorSkill;
    private _mentorCalendars: MentorCalendar[];
    private _mentorFirstName: string;
    private _mentorLastName: string;
    private _contactNo: string;

    constructor(mentor: Mentor, skill: Skill, mentorSkill: MentorSkill, mentorCalendars: MentorCalendar[], mentorFirstName: string, mentorLastName: string, contactNo: string) {
        this._mentor = mentor;
        this._skill = skill;
        this._mentorSkill = mentorSkill;
        this._mentorCalendars = mentorCalendars;
        this._mentorFirstName = mentorFirstName;
        this._mentorLastName = mentorLastName;
        this._contactNo = contactNo;
    }

    get mentor(): Mentor {
        return this._mentor;
    }

    set mentor(mentor: Mentor) {
        this._mentor = mentor;
    }

    get skill(): Skill {
        return this._skill;
    }

    set skill(skill: Skill) {
        this._skill = skill;
    }

    get mentorSkill(): MentorSkill {
        return this._mentorSkill;
    }

    set mentorSkill(mentorSkill: MentorSkill) {
        this._mentorSkill = mentorSkill;
    }

    get mentorCalendars(): MentorCalendar[] {
        return this._mentorCalendars;
    }

    set mentorCalendars(mentorCalendars: MentorCalendar[]) {
        this._mentorCalendars = mentorCalendars;
    }

    get mentorFirstName(): string {
        return this._mentorFirstName;
    }

    set mentorFirstName(mentorFirstName: string) {
        this._mentorFirstName = mentorFirstName;
    }

    get mentorLastName(): string {
        return this._mentorLastName;
    }

    set mentorLastName(mentorLastName: string) {
        this._mentorLastName = mentorLastName;
    }

    get contactNo(): string {
        return this._contactNo;
    }

    set contactNo(contactNo: string) {
        this._contactNo = contactNo;
    }

}
