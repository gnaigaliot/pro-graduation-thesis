import { Component, OnInit } from '@angular/core';
import { UserService } from '../../../core/services/user.service';
import { AppComponent } from '../../../app.component';
import { BaseComponent } from '../../../shared/components/base-component/base-component.component';
import { FormGroup, Validators } from '@angular/forms';
import { UserToken } from 'src/app/core/models/user-token.model';
import { Storage } from 'src/app/shared/service/storage.service';

@Component({
  selector: 'app-user-info-form',
  templateUrl: './user-info-form.component.html',
  styleUrls: ['./user-info-form.component.css']
})
export class UserInfoFormComponent extends BaseComponent implements OnInit {
  formSave: FormGroup;
  userInfo: UserToken;
  employeeImgUrl: string;
  formConfig = {
    fullName: ['', [Validators.required]],
    email: ['', [Validators.required]],
    password: ['', [Validators.required]],
    mobileNumber: ['', [Validators.required]],
    dateOfBirth: ['', [Validators.required]],
    gender: ['', [Validators.required]]
  };
  constructor(
    public userService: UserService,
    private app: AppComponent
  ) {
    super(null);
    this.setMainService(userService)
    this.formSave = this.buildForm({}, this.formConfig);
    this.userInfo = Storage.getUserToken();
  }

  ngOnInit() {
    this.userService.findOne(this.userInfo.userId).subscribe(res => {
      this.formSave = this.buildForm(res.data, this.formConfig);
      this.employeeImgUrl = res.data['employeeImgUrl'];
    });
  }

  /****************** CAC HAM COMMON DUNG CHUNG ****/
  get f() {
    return this.formSave.controls;
  }

  /**
* processSaveOrUpdate
*/
  processSaveOrUpdate() {
    this.app.confirmMessage(null, () => {// on accepted
      this.userService.saveOrUpdateInfo(this.formSave.value)
        .subscribe(res => {
          this.formSave = this.buildForm(res.data, this.formConfig);
        });
    }, () => {// on rejected
    });
  }
}
