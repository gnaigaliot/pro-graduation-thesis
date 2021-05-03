import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EmployeeRoutingModule } from './employee-routing.module';
import { EmployeeIndexComponent } from './employee-index/employee-index.component';
import { EmployeeSearchComponent } from './employee-search/employee-search.component';
import { EmployeeFormComponent } from './employee-form/employee-form.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { FaceDetectModule } from 'projects/face-detect/src/public-api';


@NgModule({
  declarations: [
    EmployeeIndexComponent,
    EmployeeSearchComponent,
    EmployeeFormComponent
  ],
  imports: [
    CommonModule,
    EmployeeRoutingModule,
    SharedModule,
    FaceDetectModule
  ],
  entryComponents: [
    EmployeeFormComponent
  ]
})
export class EmployeeModule { }
