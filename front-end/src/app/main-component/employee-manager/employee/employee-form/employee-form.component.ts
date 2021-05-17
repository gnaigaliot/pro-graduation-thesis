import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormGroup, Validators } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, Subject, timer } from 'rxjs';
import { take } from 'rxjs/operators';
import { AppComponent } from 'src/app/app.component';
import { DepartmentService } from 'src/app/core/services/department/department.service';
import { EmployeeService } from 'src/app/core/services/employee/employee.service';
import { PositionService } from 'src/app/core/services/position/position.service';
import { BaseComponent } from 'src/app/shared/components/base-component/base-component.component';
import { CommonUtils } from 'src/app/shared/service/common-utils.service';
import * as _ from 'lodash';

@Component({
  selector: 'app-employee-form',
  templateUrl: './employee-form.component.html',
  styleUrls: ['./employee-form.component.css']
})
export class EmployeeFormComponent extends BaseComponent implements OnInit {
  formSave: FormGroup;
  public listDepartment: any = [];
  public listPositions: any = [];

  isImageSaved: boolean;
  cardImageBase64: string;
  imageError: string;

  formConfig = {
    employeeId: [''],
    employeeCode: ['', [Validators.required]],
    employeeName: ['', [Validators.required]],
    dateOfBirth: ['', [Validators.required]],
    gender: ['', [Validators.required]],
    email: ['', [Validators.required]],
    phoneNumber: ['', [Validators.required]],
    status: [''],
    departmentId: ['', [Validators.required]],
    positionId: ['', [Validators.required]],
    employeeImgUrl: ['', [Validators.required]],
    address: ['']
  };
  constructor(
    private app: AppComponent,
    private employeeService: EmployeeService,
    public activeModal: NgbActiveModal,
    private departmentService: DepartmentService,
    private positionService: PositionService
  ) {
    super(null);
    this.formSave = this.buildForm({}, this.formConfig);
  }

  ngOnInit(): void {
    // tslint:disable-next-line: deprecation
    this.departmentService.getAllWithoutPagination().subscribe(res => {
      this.listDepartment = res;
    });

    // tslint:disable-next-line: deprecation
    this.positionService.getAllWithoutPagination().subscribe(data => {
      this.listPositions = data;
    });
  }

  // tslint:disable-next-line: typedef
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
      this.cardImageBase64 = data.employeeImgUrl;
      this.formSave = this.buildForm(data, this.formConfig);
      this.isImageSaved = true;
    }
  }

  processSaveOrUpdate(): void {
    if (!CommonUtils.isValidForm(this.formSave)) {
      return;
    }
    this.app.confirmMessage(null, () => {// on accepted
      this.employeeService.saveOrUpdate(this.formSave.value)
      // tslint:disable-next-line: deprecation
      .subscribe(res => {
        if (this.employeeService.requestIsSuccess(res)) {
          this.activeModal.close(res);
        }
      });
     }, () => {// on rejected
   });
  }

  fileChangeEvent(fileInput: any): boolean {
    this.imageError = null;
    if (fileInput.target.files && fileInput.target.files[0]) {
        // Size Filter Bytes
        const maxSize = 20971520;
        const allowedTypes = ['image/png', 'image/jpeg'];
        const maxHeight = 15200;
        const maxWidth = 25600;

        if (fileInput.target.files[0].size > maxSize) {
            this.imageError =
                'Maximum size allowed is ' + maxSize / 1000 + 'Mb';

            return false;
        }

        if (!_.includes(allowedTypes, fileInput.target.files[0].type)) {
            this.imageError = 'Only Images are allowed ( JPG | PNG )';
            return false;
        }
        const reader = new FileReader();
        reader.onload = (e: any) => {
            const image = new Image();
            image.src = e.target.result;
            image.onload = rs => {
                // tslint:disable-next-line: no-string-literal
                const imgHeight = rs.currentTarget['height'];
                // tslint:disable-next-line: no-string-literal
                const imgWidth = rs.currentTarget['width'];

                if (imgHeight > maxHeight && imgWidth > maxWidth) {
                    this.imageError =
                        'Maximum dimentions allowed ' +
                        maxHeight +
                        '*' +
                        maxWidth +
                        'px';
                    return false;
                } else {
                    const imgBase64Path = e.target.result;
                    this.cardImageBase64 = imgBase64Path;
                    this.formSave.controls.employeeImgUrl.setValue(imgBase64Path);
                    this.isImageSaved = true;
                    // this.previewImagePath = imgBase64Path;
                }
            };
        };
        reader.readAsDataURL(fileInput.target.files[0]);
    }
  }
}
