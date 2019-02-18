export class Skill {

    private _id: number;
    private _name: string;
    private _description: string;
    private _prerequisites: string;

    constructor(id: number, name: string, description: string, prerequisites: string) {
        this._id = id;
        this._name = name;
        this._description = description;
        this._prerequisites = prerequisites;
    }

    get id(): number {
        return this._id;
    }

    set id(id: number) {
        this._id = id;
    }

    get name(): string {
        return this._name;
    }

    set name(name: string) {
        this._name = name;
    }

    get description(): string {
        return this._description;
    }

    set description(description: string) {
        this._description = description;
    }

    get prerequisites(): string {
        return this._prerequisites;
    }

    set prerequisites(prerequisites: string) {
        this._prerequisites = prerequisites;
    }

}
