import { AdminSetting } from './admin-setting';

describe('AdminSetting', () => {
  it('should create an instance', () => {
    expect(new AdminSetting(0, "", "")).toBeTruthy();
  });
});
