import { Component, OnInit } from '@angular/core';
import { Validators, FormGroup } from '@angular/forms';
import { UserService } from '../../../core/services/user.service';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { AppComponent } from '../../../app.component';
import { BaseComponent } from '../../../shared/components/base-component/base-component.component';
import { CommonUtils } from '../../../shared/service/common-utils.service';
import { UserToken } from '../../../core/models/user-token.model';
import { Storage } from '../../../shared/service/storage.service';

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.css']
})
export class UserFormComponent extends BaseComponent implements OnInit {
  formSave: FormGroup;
  userInfo: UserToken;
  positionList: any;
  formConfig = {
    userId: [''],
    userName: ['', [Validators.required, Validators.maxLength(100)]],
    password: [''],
    employeeName: ['', [Validators.required, Validators.maxLength(200)]],
    dateOfBirth: [''],
    gender: [1],
    email: ['', [Validators.maxLength(50), Validators.required]],
    phoneNumber: ['', [Validators.maxLength(50)]],
    roleId: [''],
    lstRoleId: ['', [Validators.required]]
  };
  constructor(
    public activeModal: NgbActiveModal,
    public userService: UserService,
    private app: AppComponent
  ) {
    super(null);
    this.userInfo = Storage.getUserToken();
    this.userService.getRoles().subscribe(res => {
      this.initListRole(res);
    });
    this.formSave = this.buildForm({}, this.formConfig);
  }

  ngOnInit(): void {
  }

  /****************** CAC HAM COMMON DUNG CHUNG ****/
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
    if (data && data.userId > 0) {
      this.formSave = this.buildForm(data, this.formConfig);
    }
  }

  /**
   * processSaveOrUpdate
   */
  processSaveOrUpdate(): void {
    if (!CommonUtils.isValidForm(this.formSave)) {
      return;
    }
    this.app.confirmMessage(null, () => {// on accepted
      this.userService.saveOrUpdate(this.formSave.value)
        .subscribe(res => {
          if (this.userService.requestIsSuccess(res)) {
            this.activeModal.close(res);
          }
        });
    }, () => {// on rejected
    });
  }

  initListRole(data): void {
    this.positionList = [];
    if (data) {
      for (const item of data) {
        this.positionList.push({ label: item.roleName, value: item.roleId });
      }
    }
  }
}
