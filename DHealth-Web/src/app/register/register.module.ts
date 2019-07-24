import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FlexLayoutModule } from '@angular/flex-layout';
import { MatButtonModule, MatCheckboxModule, MatInputModule } from '@angular/material';

import { RegisterRoutingModule } from './register-routing.module';
import { RegisterComponent } from './register.component';
import {MatSnackBarModule  } from '@angular/material';
import { HttpModule } from '@angular/http';

@NgModule({
    imports: [
// tslint:disable-next-line: deprecation
        HttpModule,
        CommonModule,
        RegisterRoutingModule,
        MatInputModule,
        MatCheckboxModule,
        MatSnackBarModule,
        MatButtonModule,
        FlexLayoutModule.withConfig({addFlexToParent: false})
    ],
    declarations: [RegisterComponent]
})

export class RegisterModule {}
