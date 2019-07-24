import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { MatTableModule } from '@angular/material';
import { MatFormFieldModule, MatPaginatorModule } from '@angular/material';
import { MatInputModule, MatButtonModule } from '@angular/material';

import { ReportRoutingModule } from './report-routing.module';
import { ReportComponent } from './report.component';
import { MatSnackBarModule } from '@angular/material';
import { NgMultiSelectDropDownModule } from 'ng-multiselect-dropdown';
import { FormsModule, ReactiveFormsModule  } from '@angular/forms';


@NgModule({
    imports: [
        CommonModule,
        ReportRoutingModule,
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
    declarations: [ReportComponent]
})
export class ReportModule {}
