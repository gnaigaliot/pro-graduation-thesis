import { Component, OnInit } from '@angular/core';
import { FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AppComponent } from 'src/app/app.component';
import { UserService } from 'src/app/core/services/user.service';
import { BaseComponent } from 'src/app/shared/components/base-component/base-component.component';
import { CommonUtils } from 'src/app/shared/service/common-utils.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent extends BaseComponent implements OnInit {
  formSave: FormGroup;
  error: string;
  formConfig = {
    userName: ['', [Validators.required]],
    password: ['', [Validators.required]],
    fullName: ['', [Validators.required]],
    dateOfBirth: [''],
    gender: [''],
    email: ['', [Validators.required]],
    mobileNumber: [''],
    confirmPassword: ['', [Validators.required]]
  };
  constructor(
    public router: Router,
    public app: AppComponent,
    public userService: UserService
  ) {
    super(null);
    this.formSave = this.buildForm({}, this.formConfig);
  }

  ngOnInit(): void {
  }

  // tslint:disable-next-line: typedef
  get f() {
    return this.formSave.controls;
  }

  processSaveOrUpdate(): void {
    if (!CommonUtils.isValidForm(this.formSave)) {
      return;
    }
    this.app.confirmMessage(null, () => {// on accepted
      this.userService.signUp(this.formSave.value)
      // tslint:disable-next-line: deprecation
      .subscribe(res => {
        if (this.userService.requestIsSuccess(res)) {
          alert("oke")
        }
      });
     }, () => {// on rejected
   });
  }
}
