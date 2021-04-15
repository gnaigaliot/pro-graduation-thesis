import { Component, OnInit } from "@angular/core";
import { FormGroup, FormBuilder, Validators } from "@angular/forms";
import { Router } from "@angular/router";
import { HttpParams } from "@angular/common/http";
import { UserService } from 'src/app/core/services/user.service';
import { AuthService } from 'src/app/core/services/auth.service';
import { Storage } from 'src/app/shared/service/storage.service';

@Component({
  selector: "app-login",
  templateUrl: "./login.component.html",
  styleUrls: ["./login.component.css"]
})
export class LoginComponent implements OnInit {
  error: string;
  isLoading: boolean;
  loginForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private authService: AuthService,
    private userService: UserService
  ) {}

  ngOnInit() {
    if(Storage.getUserToken() != null) {
      this.router.navigate(["starter"]);
      return;
    }
    window.sessionStorage.removeItem("token");
    this.buildForm();
  }

  get f() {
    return this.loginForm.controls;
  }

  login() {
    if (this.loginForm.invalid) {
      return;
    }

    this.authService.actionRequestToken(this.loginForm.value).subscribe(
      data => {
        if (data) {
          window.sessionStorage.setItem("token", data);
          this.authService.getCurrentUserInfo().subscribe(res => {
            const user = this.authService.extractTokenData(res);
            Storage.clear();
            Storage.setUserToken(user);
            this.router.navigate([""]);
          });
        } else {
          this.error = "Tên đăng nhập hoặc mật khẩu không đúng";
        }
      },
      error => {
        this.error = "Đăng nhập lỗi, vui lòng thử lại";
      }
    );
  }

  private buildForm(): void {
    this.loginForm = this.formBuilder.group({
      userName: ["", Validators.required],
      password: ["", Validators.required]
    });
  }
}
