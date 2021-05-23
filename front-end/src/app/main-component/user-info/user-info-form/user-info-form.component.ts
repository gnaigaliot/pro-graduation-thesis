import { Component, OnInit } from '@angular/core';
import { UserService } from '../../../core/services/user.service';
import { AppComponent } from '../../../app.component';
import { BaseComponent } from '../../../shared/components/base-component/base-component.component';
import { FormGroup, Validators } from '@angular/forms';
import { UserToken } from 'src/app/core/models/user-token.model';
import { Storage } from 'src/app/shared/service/storage.service';
import * as _ from 'lodash';
import { CommonUtils } from 'src/app/shared/service/common-utils.service';
import { EmployeeService } from 'src/app/core/services/employee/employee.service';

@Component({
  selector: 'app-user-info-form',
  templateUrl: './user-info-form.component.html',
  styleUrls: ['./user-info-form.component.css']
})
export class UserInfoFormComponent extends BaseComponent implements OnInit {
  formSave: FormGroup;
  formChangeAvatar: FormGroup;
  userInfo: UserToken;
  employeeImgUrl: string;
  departmentName: string;
  positionName: string;
  employeeCode: string;
  imageError: string;

  formConfig = {
    employeeName: ['', [Validators.required]],
    email: ['', [Validators.required]],
    password: ['', [Validators.required]],
    phoneNumber: ['', [Validators.required]],
    dateOfBirth: ['', [Validators.required]],
    gender: ['', [Validators.required]],
    userId: ['']
  };
  formChangeAvt = {
    employeeId: [''],
    employeeImgUrl: ['']
  };
  constructor(
    public userService: UserService,
    public employeeService: EmployeeService,
    private app: AppComponent
  ) {
    super(null);
    this.setMainService(userService);
    this.formSave = this.buildForm({}, this.formConfig);
    this.formChangeAvatar = this.buildForm({}, this.formChangeAvt);
    this.userInfo = Storage.getUserToken();
  }

  ngOnInit(): void {
    this.userService.findOne(this.userInfo.userId).subscribe(res => {
      this.formSave = this.buildForm(res.data, this.formConfig);
      this.employeeImgUrl = res.data.employeeImgUrl;
      this.departmentName = res.data.departmentName;
      this.positionName = res.data.positionName;
      this.employeeCode = res.data.employeeCode;
      this.formChangeAvatar.controls.employeeId.setValue(res.data.employeeId);
    });
  }

  /****************** CAC HAM COMMON DUNG CHUNG ****/
  // tslint:disable-next-line: typedef
  get f() {
    return this.formSave.controls;
  }

  processSaveOrUpdate(): void {
    if (!CommonUtils.isValidForm(this.formSave)) {
      return;
    }
    this.app.confirmMessage(null, () => {// on accepted
      this.userService.saveOrUpdateInfo(this.formSave.value)
        .subscribe(res => {
          this.formSave = this.buildForm(res.data, this.formConfig);
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
                    this.employeeImgUrl = imgBase64Path;
                    this.formChangeAvatar.controls.employeeImgUrl.setValue(imgBase64Path);
                    this.updateAvatar(imgBase64Path);
                }
            };
        };
        reader.readAsDataURL(fileInput.target.files[0]);
    }
  }

  public updateAvatar(imgBase64Path: string): void {
    this.app.confirmMessage(null, () => {// on accepted
      this.employeeService.changeAvatar(this.formChangeAvatar.value).subscribe(res => {
        Storage.getUserToken().employeeImgUrl = this.employeeImgUrl;
      });
    }, () => {// on rejected
    });
  }
}
