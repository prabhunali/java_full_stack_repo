export class Skill {

    id: number;
    name: string;
    description: string;
    prerequisites: string;

    constructor(id: number, name: string, description: string, prerequisites: string) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.prerequisites = prerequisites;
    }

    // get Id(): number {
    //     return this.id;
    // }

    // set Id(id: number) {
    //     this.id = id;
    // }

    // get name(): string {
    //     return this._name;
    // }

    // set name(name: string) {
    //     this._name = name;
    // }

    // get description(): string {
    //     return this._description;
    // }

    // set description(description: string) {
    //     this._description = description;
    // }

    // get prerequisites(): string {
    //     return this._prerequisites;
    // }

    // set prerequisites(prerequisites: string) {
    //     this._prerequisites = prerequisites;
    // }

}
