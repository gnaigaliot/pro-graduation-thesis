import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RoleIndexComponent } from './role-index/role-index.component';

const routes: Routes = [
  {
    path: '',
    component: RoleIndexComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RoleRoutingModule { }
