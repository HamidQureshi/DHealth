import { NgModule, Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FlexLayoutModule } from '@angular/flex-layout';
import { CreateProfileRoutingModule } from './create-profile-routing.module';
import { CreateProfileComponent } from './create-profile.component';
import {MatSnackBarModule } from '@angular/material';
import {
    MatAutocompleteModule, MatCardModule, MatCheckboxModule, MatDatepickerModule,
    MatFormFieldModule, MatInputModule, MatNativeDateModule, MatRadioModule, MatSelectModule,
    MatSliderModule, MatSlideToggleModule, MatButtonModule
} from '@angular/material';


@NgModule({
    imports: [
        CommonModule,
        MatAutocompleteModule,
        MatSlideToggleModule,
        MatFormFieldModule,
        MatInputModule,
        MatCardModule,
        MatButtonModule,
        MatCheckboxModule,
        MatRadioModule,
        MatDatepickerModule,
        MatNativeDateModule,
        MatFormFieldModule,
        MatSelectModule,
        MatSliderModule,
        MatRadioModule,
        CommonModule,
        CreateProfileRoutingModule,
        MatInputModule,
        MatCheckboxModule,
        MatSnackBarModule,
        FlexLayoutModule.withConfig({addFlexToParent: false})
        ],
    declarations: [CreateProfileComponent,
    ]
})

export class CreateProfileModule {}
