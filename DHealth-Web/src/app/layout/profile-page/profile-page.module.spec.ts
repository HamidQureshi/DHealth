import { ProfilePageModule } from './profile-page.moduledule';

describe('ProfilePageModule', () => {
    let ProfilePageModule: ProfilePageModule;

    beforeEach(() => {
        ProfilePageModule = new ProfilePageModule();
    });

    it('should create an instance', () => {
        expect(ProfilePageModule).toBeTruthy();
    });
});
