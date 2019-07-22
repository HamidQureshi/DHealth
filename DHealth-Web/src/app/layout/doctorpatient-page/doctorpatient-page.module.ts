import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DoctorpatientPageRoutingModule } from './doctorpatient-page-routing.module';
import { DoctorpatientPageComponent } from './doctorpatient-page.component';
import { FlexLayoutModule } from '@angular/flex-layout';
import { MatRadioModule, MatButtonModule, MatCheckboxModule, MatInputModule } from '@angular/material';
import {MatSnackBarModule } from '@angular/material';

import { MatCardModule, MatIconModule, MatTableModule } from '@angular/material';
import { MatGridListModule } from '@angular/material/grid-list';


@NgModule({
    imports: [
        MatRadioModule,
        CommonModule,
        DoctorpatientPageRoutingModule,
        MatInputModule,
        MatCheckboxModule,
        MatSnackBarModule,
        MatButtonModule,
        MatGridListModule,
        MatCardModule,
        MatCardModule,
        MatTableModule,
        MatButtonModule,
        MatIconModule,
        FlexLayoutModule.withConfig({addFlexToParent: false})
        ],
    declarations: [DoctorpatientPageComponent]
})
export class DoctorpatientPageModule {}
