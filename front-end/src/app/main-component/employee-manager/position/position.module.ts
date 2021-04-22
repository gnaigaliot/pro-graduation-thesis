import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PositionRoutingModule } from './position-routing.module';
import { PositionIndexComponent } from './position-index/position-index.component';
import { PositionFormComponent } from './position-form/position-form.component';
import { PositionSearchComponent } from './position-search/position-search.component';
import { SharedModule } from 'src/app/shared/shared.module';


@NgModule({
  declarations: [
    PositionIndexComponent,
    PositionFormComponent,
    PositionSearchComponent
  ],
  imports: [
    CommonModule,
    PositionRoutingModule,
    SharedModule
  ],
  entryComponents: [PositionFormComponent],
})
export class PositionModule { }
