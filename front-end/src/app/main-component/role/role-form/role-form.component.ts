import { Component, OnInit } from '@angular/core';
import { FormGroup, Validators } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { AppComponent } from 'src/app/app.component';
import { RoleService } from 'src/app/core/services/role/role.service';
import { BaseComponent } from 'src/app/shared/components/base-component/base-component.component';
import { CommonUtils } from 'src/app/shared/service/common-utils.service';

@Component({
  selector: 'app-role-form',
  templateUrl: './role-form.component.html',
  styleUrls: ['./role-form.component.css']
})
export class RoleFormComponent extends BaseComponent implements OnInit {
  formSave: FormGroup;
  formConfig = {
    roleId: [''],
    role: ['', [Validators.required, Validators.maxLength(50)]],
    roleName: ['', [Validators.required, Validators.maxLength(200)]],
    description: ['', [Validators.maxLength(500)]]
  };

  constructor(
    public roleService: RoleService,
    public activeModal: NgbActiveModal,
    public app: AppComponent
  ) {
    super(null);
    this.setMainService(roleService);
    this.formSave = this.buildForm({}, this.formConfig);
  }

  ngOnInit(): void {
  }

  // tslint:disable-next-line:typedef
  get f() {
    return this.formSave.controls;
  }

  public setFormValue(propertyConfigs: any, data?: any): void {
    this.propertyConfigs = propertyConfigs;
    if (data && data.roleId > 0) {
      this.formSave = this.buildForm(data, this.formConfig);
    }
  }

  processSaveOrUpdate(): void {
    if (!CommonUtils.isValidForm(this.formSave)) {
      return;
    }
    this.app.confirmMessage(null, () => {// on accepted
      this.roleService.saveOrUpdate(this.formSave.value)
      // tslint:disable-next-line: deprecation
      .subscribe(res => {
        if (this.roleService.requestIsSuccess(res)) {
          this.activeModal.close(res);
        }
      });
     }, () => {// on rejected
    });
  }
}
