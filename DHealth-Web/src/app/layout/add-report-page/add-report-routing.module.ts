import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AddReportComponent } from './add-report.component';

const routes: Routes = [
    {
        path: '',
        component: AddReportComponent
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class AddReportRoutingModule {}
