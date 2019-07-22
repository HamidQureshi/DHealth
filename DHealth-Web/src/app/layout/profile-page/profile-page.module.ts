import { NgModule ,Renderer} from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProfilePageRoutingModule } from './profile-page-routing.module';
import { ProfilePageComponent } from './profile-page.component';
import { FlexLayoutModule } from '@angular/flex-layout';
import { MatRadioModule, MatButtonModule, MatCheckboxModule, MatInputModule, MatDatepickerModule, MatNativeDateModule } from '@angular/material';
import {MatSnackBarModule } from '@angular/material';
import { MatCardModule, MatIconModule, MatTableModule } from '@angular/material';

@NgModule({
    imports: [
        MatRadioModule,
        CommonModule,
        ProfilePageRoutingModule,
        MatInputModule,
        MatCheckboxModule,
        MatSnackBarModule,
        MatButtonModule,
        MatCardModule,
        MatTableModule,
        MatIconModule,
        MatDatepickerModule,
        MatNativeDateModule,
        FlexLayoutModule.withConfig({addFlexToParent: false})
        ],
    declarations: [ProfilePageComponent]
})
export class ProfilePageModule {}
