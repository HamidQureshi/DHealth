import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LayoutComponent } from './layout.component';

const routes: Routes = [
    {
        path: '',
        component: LayoutComponent,
        children: [
            {
                path: '',
                redirectTo: 'dashboard'
            },
            {
                path: 'dashboard',
                loadChildren: './profile-page/profile-page.module#ProfilePageModule'
            },
            {
                path: 'components',
                loadChildren:
                    './material-components/material-components.module#MaterialComponentsModule'
            },
            {
                path: 'profile-page',
                loadChildren: './profile-page/profile-page.module#ProfilePageModule'
            },
            {
                path: 'doctorpatient-page',
                loadChildren: './doctorpatient-page/doctorpatient-page.module#DoctorpatientPageModule'
            },
            {
                path: 'report-list',
                loadChildren: './report-list/reports.module#ReportsModule'
            }
            ,
            {
                path: 'report',
                loadChildren: './report-page/report.module#ReportModule'
            }
            ,
            {
                path: 'add-report',
                loadChildren: './add-report-page/add-report.module#AddReportModule'
            }
        ]
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class LayoutRoutingModule {}
