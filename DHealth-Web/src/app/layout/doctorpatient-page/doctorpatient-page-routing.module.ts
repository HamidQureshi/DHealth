import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DoctorpatientPageComponent } from './doctorpatient-page.component';

const routes: Routes = [
    {
        path: '',
        component: DoctorpatientPageComponent
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class DoctorpatientPageRoutingModule {}
