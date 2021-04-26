import { Component, OnInit } from '@angular/core';
import { FormGroup, Validators } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { AppComponent } from 'src/app/app.component';
import { DepartmentService } from 'src/app/core/services/department/department.service';
import { BaseComponent } from 'src/app/shared/components/base-component/base-component.component';
import { CommonUtils } from 'src/app/shared/service/common-utils.service';

@Component({
  selector: 'app-department-form',
  templateUrl: './department-form.component.html',
  styleUrls: ['./department-form.component.css']
})
export class DepartmentFormComponent extends BaseComponent implements OnInit {
  formSave: FormGroup;
  formConfig = {
    departmentId: [''],
    departmentCode: ['', [Validators.required]],
    departmentName: ['', [Validators.required]],
    statusDepartment: ['']
  };
  constructor(
    private app: AppComponent,
    private departmentService: DepartmentService,
    public activeModal: NgbActiveModal
  ) {
    super(null);
    this.formSave = this.buildForm({}, this.formConfig);
  }

  ngOnInit(): void {
  }

  // tslint:disable-next-line:typedef
  get f() {
    return this.formSave.controls;
  }

  /**
   * setFormValue
   * param data
   */
   public setFormValue(propertyConfigs: any, data?: any): void {
    this.propertyConfigs = propertyConfigs;
    if (data && data.departmentId > 0) {
      this.formSave = this.buildForm(data, this.formConfig);
    }
  }

  processSaveOrUpdate(): void {
    if (!CommonUtils.isValidForm(this.formSave)) {
      return;
    }
    this.app.confirmMessage(null, () => {// on accepted
      this.departmentService.saveOrUpdate(this.formSave.value)
      .subscribe(res => {
        if (this.departmentService.requestIsSuccess(res)) {
          this.activeModal.close(res);
        }
      });
     }, () => {// on rejected
   });
  }
}
