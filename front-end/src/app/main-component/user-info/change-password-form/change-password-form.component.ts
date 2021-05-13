import { Component, OnInit } from '@angular/core';
import { UserToken } from '../../../core/models/user-token.model';
import { FormGroup, Validators } from '@angular/forms';
import { UserService } from '../../../core/services/user.service';
import { AppComponent } from '../../../app.component';
import { BaseComponent } from '../../../shared/components/base-component/base-component.component';
import { Storage } from '../../../shared/service/storage.service';
import { CommonUtils } from 'src/app/shared/service/common-utils.service';

@Component({
  selector: 'app-change-password-form',
  templateUrl: './change-password-form.component.html',
  styleUrls: ['./change-password-form.component.css']
})
export class ChangePasswordFormComponent extends BaseComponent implements OnInit {
  userInfo: UserToken;
  formSave: FormGroup;
  passwordCompare: string;
  formConfig = {
    userId: [''],
    userName: ['', [Validators.required, Validators.maxLength(100)]],
    newPassword: ['', [Validators.required]],
    oldPassword: ['', [Validators.required]],
    repeatPassword: ['', [Validators.required]]
  };
  constructor(
    public userService: UserService,
    private app: AppComponent
  ) {
      super(null);
      this.formSave = this.buildForm({}, this.formConfig);
      this.userInfo = Storage.getUserToken();
   }

  ngOnInit(): void {
    this.userService.findOne(this.userInfo.userId).subscribe(res => {
      this.formSave = this.buildForm(res.data, this.formConfig);
      this.passwordCompare = res.data.password;
    });
  }

  /****************** CAC HAM COMMON DUNG CHUNG ****/
  // tslint:disable-next-line: typedef
  get f() {
    return this.formSave.controls;
  }

  public validateBeforeSave(): boolean {
    if (this.passwordCompare !== this.formSave.controls.oldPassword.value) {
      this.app.errorMessage('Mật khẩu cũ không chính xác');
      return false;
    }
    if (this.formSave.controls.newPassword.value.length < 6) {
      this.app.errorMessage('Mật khẩu mới phải có ít nhất 6 ký tự');
      return false;
    }
    if (this.formSave.controls.oldPassword.value === this.formSave.controls.newPassword.value) {
      this.app.errorMessage('Mật khẩu cũ và mật khẩu mới không được trùng nhau');
      return false;
    }
    if (this.formSave.controls.newPassword.value !== this.formSave.controls.repeatPassword.value) {
      this.app.errorMessage('Xác nhận mật khẩu mới không chính xác');
      return false;
    }
    return true;
  }

  processSaveOrUpdate(): void {
    if (!CommonUtils.isValidForm(this.formSave)) {
      return;
    }
    if (!this.validateBeforeSave()) {
      return;
    }
    this.app.confirmMessage(null, () => {// on accepted
      this.userService.changePassword(this.formSave.value)
      .subscribe(res => {
        this.formSave = this.buildForm(res.data, this.formConfig);
      });
     }, () => {// on rejected
   });
  }
}
