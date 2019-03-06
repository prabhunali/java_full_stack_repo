import { Role } from './role';

export class User {

    id: number
    username: string;
    password: string;
    firstName: string;
    lastName: string;
    contactNumber: string;
    registrationCode: string;
    active: boolean;
    roles: Role[];
    //roleName: string;

    constructor() { }

}