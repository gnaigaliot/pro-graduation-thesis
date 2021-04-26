import { Component, OnInit } from '@angular/core';
import { FormGroup, Validators } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { AppComponent } from 'src/app/app.component';
import { EmployeeService } from 'src/app/core/services/employee/employee.service';
import { BaseComponent } from 'src/app/shared/components/base-component/base-component.component';
import { CommonUtils } from 'src/app/shared/service/common-utils.service';

@Component({
  selector: 'app-employee-form',
  templateUrl: './employee-form.component.html',
  styleUrls: ['./employee-form.component.css']
})
export class EmployeeFormComponent extends BaseComponent implements OnInit {
  formSave: FormGroup;
  formConfig = {
    employeeId: [''],
    employeeCode: ['', [Validators.required]],
    employeeName: ['', [Validators.required]],
    dateOfBirth: ['', [Validators.required]],
    gender: ['', [Validators.required]],
    email: ['', [Validators.required]],
    phoneNumber: ['', [Validators.required]],
    status: ['']
  };
  constructor(
    private app: AppComponent,
    private employeeService: EmployeeService,
    public activeModal: NgbActiveModal
  ) {
    super(null);
    this.formSave = this.buildForm({}, this.formConfig);
  }

  ngOnInit(): void {
  }

  get f() {
    return this.formSave.controls;
  }

  /**
   * setFormValue
   * param data
   */
   public setFormValue(propertyConfigs: any, data?: any): void {
    this.propertyConfigs = propertyConfigs;
    if (data && data.employeeId > 0) {
      this.formSave = this.buildForm(data, this.formConfig);
    }
  }

  processSaveOrUpdate(): void {
    if (!CommonUtils.isValidForm(this.formSave)) {
      return;
    }
    this.app.confirmMessage(null, () => {// on accepted
      this.employeeService.saveOrUpdate(this.formSave.value)
      .subscribe(res => {
        if (this.employeeService.requestIsSuccess(res)) {
          this.activeModal.close(res);
        }
      });
     }, () => {// on rejected
   });
  }

}
