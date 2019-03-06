export class SignupUser {

    private id: number
    private username: string;
    private password: string;
    private firstName: string;
    private lastName: string;
    private contactNumber: string;
    private registrationCode: string;
    private roleName: string;

    constructor(
        username: string,
        password: string,
        firstName: string,
        lastName: string,
        contactNumber: string,
        roleName: string
    ) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactNumber = contactNumber;
        this.roleName = roleName;
     }

}
