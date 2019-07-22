import { CreateProfileModule } from './create-profile.module';

describe('CreateProfileModule', () => {
    let createProfileModule: CreateProfileModule;

    beforeEach(() => {
        createProfileModule = new CreateProfileModule();
    });

    it('should create an instance', () => {
        expect(createProfileModule).toBeTruthy();
    });
});
