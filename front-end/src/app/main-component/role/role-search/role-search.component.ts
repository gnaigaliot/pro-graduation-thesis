import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { RoleService } from 'src/app/core/services/role/role.service';
import { BaseComponent } from 'src/app/shared/components/base-component/base-component.component';
import { RoleFormComponent } from '../role-form/role-form.component';

@Component({
  selector: 'app-role-search',
  templateUrl: './role-search.component.html',
  styleUrls: ['./role-search.component.css']
})
export class RoleSearchComponent extends BaseComponent implements OnInit {
  formSearch: FormGroup;
  formConfig = {
    roleId: [''],
    role: [''],
    roleName: ['']
  };
  constructor(
    public roleService: RoleService,
    public modalService: NgbModal
  ) {
    super(null);
    this.setMainService(roleService);
    this.formSearch = this.buildForm({}, this.formConfig);
  }

  ngOnInit(): void {
    this.processSearch();
  }

  // tslint:disable-next-line:typedef
  public get f() {
    return this.formSearch.controls;
  }

  public prepareSaveOrUpdate(item?: any): void {
    if (item && item > 0) {
      this.roleService.findOne(item)
        .subscribe(res => {
          this.activeFormModal(this.modalService, RoleFormComponent, res.data);
        });
    } else {
      this.activeFormModal(this.modalService, RoleFormComponent, null);
    }
  }
}
