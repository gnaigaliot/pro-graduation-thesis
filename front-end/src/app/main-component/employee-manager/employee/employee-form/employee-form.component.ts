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

@Component({
  selector: 'app-employee-form',
  templateUrl: './employee-form.component.html',
  styleUrls: ['./employee-form.component.css']
})
export class EmployeeFormComponent extends BaseComponent implements OnInit, OnDestroy {
  formSave: FormGroup;
  public listDepartment: any = [];
  public listPositions: any = [];
  public idEmployee: any;
  public listImageFace: string[] = [];
  // face = 'assets/images/face-Iris-capture.jpg';
  private trigger: Subject<void> = new Subject<void>();
  // tslint:disable-next-line: no-inferrable-types
  public percentCapture: number = 0;
  // tslint:disable-next-line: no-inferrable-types
  public isShow: boolean = true;

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
    employeeImgUrl: ['', [Validators.required]]
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
      this.listImageFace = data.employeeImgUrl;
      this.formSave = this.buildForm(data, this.formConfig);
      this.isShow = false;
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

  public get triggerObservable(): Observable<void> {
    return this.trigger.asObservable();
  }

  public viewChange(event): void {
    this.listImageFace.push(event.face);
    this.formSave.controls.employeeImgUrl.setValue(this.listImageFace);
    // this.face = event.face;
  }

  public triggerSnapshot(): void {
    timer(0, 200).pipe(
      take(100)
    // tslint:disable-next-line: deprecation
    ).subscribe(() => {
      this.trigger.next();
      this.percentCapture = this.percentCapture + 1;
    });
  }

  ngOnDestroy(): void {
    this.trigger.complete();
  }

  public updateSnapshot(): void {
    this.listImageFace = [];
    this.isShow = true;
  }
}
