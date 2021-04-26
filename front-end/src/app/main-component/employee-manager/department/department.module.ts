import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DepartmentRoutingModule } from './department-routing.module';
import { DepartmentIndexComponent } from './department-index/department-index.component';
import { DepartmentSearchComponent } from './department-search/department-search.component';
import { DepartmentFormComponent } from './department-form/department-form.component';
import { SharedModule } from 'src/app/shared/shared.module';


@NgModule({
  declarations: [
    DepartmentIndexComponent,
    DepartmentSearchComponent,
    DepartmentFormComponent
  ],
  imports: [  
    CommonModule,
    DepartmentRoutingModule,
    SharedModule
  ],
  entryComponents: [
    DepartmentFormComponent
  ]
})
export class DepartmentModule { }
