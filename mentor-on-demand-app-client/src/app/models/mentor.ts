export class Mentor {

    id: number;
    userId: number;
    username: string;
    linkedinUrl: string;
    yearsOfExperience: number;
    introduction: string;

    constructor(id: number, userId: number, username: string, linkedinUrl: string, yearsOfExperience: number, introduction: string) {
        this.id = id;
        this.userId = userId;
        this.username = username;
        this.linkedinUrl = linkedinUrl;
        this.yearsOfExperience = yearsOfExperience;
        this.introduction = introduction;
    }
}
