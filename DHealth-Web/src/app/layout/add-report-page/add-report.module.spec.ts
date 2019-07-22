import { AddReportModule } fro./add-report.module.module';

describe('AddReportModule', () => {
    let addreportModule: AddReportModule;

    beforeEach(() => {
        addreportModule = new AddReportModule();
    });

    it('should create an instance', () => {
        expect(addreportModule).toBeTruthy();
    });
});
