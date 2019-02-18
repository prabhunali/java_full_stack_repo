import { last } from 'rxjs/operators';

export class UserProfile {

    id: number;
    firstName: string;
    lastName: string;
    totalYearsExp: number;
    contactNumber: string;
    linkedInUrl: string;
    introduction: string;

    constructor(id: number
        , firstName: string
        , lastName: string
        , totalYearsExp: number
        , contactNumber: string
        , linkedInUrl: string
        , introduction: string) {

            this.id = id;
            this.firstName  = firstName;
            this.lastName = lastName;
            this.totalYearsExp = totalYearsExp;
            this.contactNumber = contactNumber;
            this.linkedInUrl = linkedInUrl;
            this.introduction = introduction;
    }

}
