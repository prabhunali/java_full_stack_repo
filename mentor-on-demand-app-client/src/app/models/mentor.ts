export class Mentor {

    private _id: number;
    private _username; string;
    private _linkedinUrl: string;
    private _yearsOfExperience: number;
    private _introduction: string;

    constructor(id: number, username: string, linkedinUrl: string, yearsOfExperience: number, introduction: string) {
        this._id = id;
        this._username = username;
        this._linkedinUrl = linkedinUrl;
        this._yearsOfExperience = yearsOfExperience;
        this._introduction = introduction;
    }

    get id(): number {
        return this._id;
    }

    set id(id: number) {
        this._id = id;
    }

    get username(): string {
        return this._username;
    }

    set username(username: string) {
        this._username = username;
    }

    get linkedinUrl(): string {
        return this._linkedinUrl;
    }

    set linkedinUrl(linkedinUrl: string) {
        this._linkedinUrl = linkedinUrl;
    }

    get yearsOfExperience(): number {
        return this._yearsOfExperience;
    }

    set yearsOfExperience(yearsOfExperience: number) {
        this._yearsOfExperience = yearsOfExperience;
    }

    get introduction(): string {
        return this._introduction;
    }

    set introduction(introduction: string) {
        this._introduction = introduction;
    }

}
