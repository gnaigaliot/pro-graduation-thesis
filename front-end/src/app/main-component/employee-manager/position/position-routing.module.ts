import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PositionIndexComponent } from './position-index/position-index.component';

const routes: Routes = [
  {
    path: '',
    component: PositionIndexComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PositionRoutingModule { }
