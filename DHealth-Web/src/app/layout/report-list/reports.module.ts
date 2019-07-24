import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { MatTableModule } from '@angular/material';
import { MatFormFieldModule, MatPaginatorModule } from '@angular/material';
import { MatInputModule } from '@angular/material';

import { ReportsRoutingModule } from './reports-routing.module';
import { ReportsComponent } from './reports.component';
import { MatSnackBarModule } from '@angular/material';
import { NgxFabModule } from 'ngx-fab';


@NgModule({
    imports: [
        CommonModule,
        ReportsRoutingModule,
        MatTableModule,
        MatFormFieldModule,
        MatPaginatorModule,
        MatInputModule,
        MatSnackBarModule,
        NgxFabModule
    ],
    declarations: [ReportsComponent]
})
export class ReportsModule {}
