import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RoleRoutingModule } from './role-routing.module';
import { SharedModule } from 'src/app/shared/shared.module';
import { RoleIndexComponent } from './role-index/role-index.component';
import { RoleFormComponent } from './role-form/role-form.component';
import { RoleSearchComponent } from './role-search/role-search.component';


@NgModule({
  declarations: [
    RoleIndexComponent,
    RoleFormComponent,
    RoleSearchComponent
  ],
  imports: [
    CommonModule,
    RoleRoutingModule,
    SharedModule
  ],
  entryComponents: [RoleFormComponent]
})
export class RoleModule { }
