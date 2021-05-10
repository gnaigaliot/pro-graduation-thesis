import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TimekeepingIndexComponent } from './timekeeping-index/timekeeping-index.component';

const routes: Routes = [
  {
    path: '',
    component: TimekeepingIndexComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TimekeepingRoutingModule { }
