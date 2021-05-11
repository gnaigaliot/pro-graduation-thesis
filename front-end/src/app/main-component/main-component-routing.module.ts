import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  {
    path: 'user',
    loadChildren: () => import('./user/user.module').then(m => m.UserModule)
  },
  {
    path: 'employee-manager',
    loadChildren: () => import('./employee-manager/employee-manager.module').then(m => m.EmployeeManagerModule)
  },
  {
    path: 'timekeeping-manager',
    loadChildren: () => import('./timekeeping/timekeeping.module').then(m => m.TimekeepingModule)
  },
  {
    path: 'user-info',
    loadChildren: () => import('./user-info/user-info.module').then(m => m.UserInfoModule)
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MainComponentRoutingModule { }
