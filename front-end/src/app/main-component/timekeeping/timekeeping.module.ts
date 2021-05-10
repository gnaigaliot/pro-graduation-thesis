import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TimekeepingRoutingModule } from './timekeeping-routing.module';
import { SharedModule } from 'src/app/shared/shared.module';
import { TimekeepingIndexComponent } from './timekeeping-index/timekeeping-index.component';
import { TimekeepingSearchComponent } from './timekeeping-search/timekeeping-search.component';


@NgModule({
  declarations: [
    TimekeepingIndexComponent,
    TimekeepingSearchComponent
  ],
  imports: [
    CommonModule,
    TimekeepingRoutingModule,
    SharedModule
  ]
})
export class TimekeepingModule { }
