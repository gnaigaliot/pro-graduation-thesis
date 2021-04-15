import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MainComponentRoutingModule } from './main-component-routing.module';
import { SharedModule } from '../shared/shared.module';
import { RequestResetComponent } from './request-reset/request-reset.component';
import { DemoMaterialModule } from '../demo-material-module';
import { LoginComponent } from './login/login.component';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [LoginComponent, RequestResetComponent],
  imports: [
    CommonModule,
    MainComponentRoutingModule,
    SharedModule,
    ReactiveFormsModule,
    DemoMaterialModule
  ],
  providers: [
  ],
  entryComponents: []
})
export class MainComponentModule { }
