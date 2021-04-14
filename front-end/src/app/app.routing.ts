import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './core/guards';

import { FullComponent } from './layouts/full/full.component';
import { LoginComponent } from './main-component/login/login.component';

export const AppRoutes: Routes = [
  {
    path: '',
    component: FullComponent,
    canActivate: [AuthGuard],
    children: [
      {
        path: '',
        redirectTo: '/dashboard',
        pathMatch: 'full'
      },
      {
        path: '',
        loadChildren:
          () => import('./material-component/material.module').then(m => m.MaterialComponentsModule)
      },
      {
        path: '',
        loadChildren: () => import('./main-component/main-component.module').then(m => m.MainComponentModule)
      }
    ]
  },
  {
    path: 'login',
    component: LoginComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(AppRoutes, { onSameUrlNavigation: 'reload' })],
  exports: [RouterModule],
  providers: []
})
export class AppRoutingModule {}
