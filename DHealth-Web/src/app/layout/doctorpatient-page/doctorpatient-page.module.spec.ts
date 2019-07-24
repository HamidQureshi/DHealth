import { DoctorpatientPageModule } from './doctorpatient-page.module';

describe('doctorpatientPageModule', () => {
    let DoctorpatientPageModule: DoctorpatientPageModule;

    beforeEach(() => {
        DoctorpatientPageModule = new DoctorpatientPageModule();
    });

    it('should create an instance', () => {
        expect(DoctorpatientPageModule).toBeTruthy();
    });
});
