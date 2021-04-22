import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EmployeeManagerRoutingModule } from './employee-manager-routing.module';
import { SharedModule } from 'src/app/shared/shared.module';


@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    EmployeeManagerRoutingModule,
    SharedModule
  ]
})
export class EmployeeManagerModule { }
