import { Skill } from './skill';

describe('Skill', () => {
  it('should create an instance', () => {
    let id: number;
    let name: string;
    let description: string;
    let prerequisites: string;
    expect(new Skill(id, name, description, prerequisites)).toBeTruthy();
  });
});
