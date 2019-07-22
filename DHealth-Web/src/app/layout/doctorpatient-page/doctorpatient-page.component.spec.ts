import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DoctorpatientPageComponent } from './doctorpatient-page.component';

describe('DoctorpatientPageComponent', () => {
    let component: DoctorpatientPageComponent;
    let fixture: ComponentFixture<DoctorpatientPageComponent>;

    beforeEach(
        async(() => {
            TestBed.configureTestingModule({
                declarations: [DoctorpatientPageComponent]
            }).compileComponents();
        })
    );

    beforeEach(() => {
        fixture = TestBed.createComponent(DoctorpatientPageComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
