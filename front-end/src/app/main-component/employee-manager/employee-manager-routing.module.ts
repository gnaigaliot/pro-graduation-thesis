import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PositionIndexComponent } from './position/position-index/position-index.component';

const routes: Routes = [
  {
    path: '',
    children: [
      {
        path: 'positions',
        loadChildren: () => import('./position/position.module').then(m => m.PositionModule)
      },
      {
        path: 'departments',
        loadChildren: () => import('./department/department.module').then(m => m.DepartmentModule)
      },
      {
        path: 'employees',
        loadChildren: () => import('./employee/employee.module').then(m => m.EmployeeModule)
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EmployeeManagerRoutingModule { }
