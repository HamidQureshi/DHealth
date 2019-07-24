import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { MatTableModule } from '@angular/material';
import { MatFormFieldModule, MatPaginatorModule } from '@angular/material';
import { MatInputModule, MatButtonModule } from '@angular/material';

import { AddReportRoutingModule } from './add-report-routing.module';
import { AddReportComponent } from './add-report.component';
import { MatSnackBarModule } from '@angular/material';
import { NgMultiSelectDropDownModule } from 'ng-multiselect-dropdown';
import { FormsModule, ReactiveFormsModule  } from '@angular/forms';


@NgModule({
    imports: [
        CommonModule,
        AddReportRoutingModule,
        MatTableModule,
        MatFormFieldModule,
        MatPaginatorModule,
        MatInputModule,
        MatSnackBarModule,
        MatButtonModule,
        NgMultiSelectDropDownModule,
        FormsModule,
        ReactiveFormsModule
            ],
    declarations: [AddReportComponent]
})
export class AddReportModule {}
