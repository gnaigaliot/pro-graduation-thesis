import { Component, OnInit } from '@angular/core';
import { FormGroup, Validators } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { AppComponent } from 'src/app/app.component';
import { PositionService } from 'src/app/core/services/position/position.service';
import { BaseComponent } from 'src/app/shared/components/base-component/base-component.component';
import { CommonUtils } from 'src/app/shared/service/common-utils.service';

@Component({
  selector: 'app-position-form',
  templateUrl: './position-form.component.html',
  styleUrls: ['./position-form.component.css']
})
export class PositionFormComponent extends BaseComponent implements OnInit {
  public formSave: FormGroup;
  formConfig = {
    positionId: [''],
    positionCode: ['', [Validators.maxLength(10), Validators.required]],
    positionName: ['', [Validators.maxLength(50), Validators.required]],
    salary: ['']
  };

  constructor(
    private app: AppComponent,
    private positionService: PositionService,
    public activeModal: NgbActiveModal
  ) {
    super(null);
    this.setMainService(positionService);
    this.formSave = this.buildForm({}, this.formConfig);
  }

  ngOnInit(): void {
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
    if (data && data.positionId > 0) {
      this.formSave = this.buildForm(data, this.formConfig);
    }
  }

  processSaveOrUpdate(): void {
    if (!CommonUtils.isValidForm(this.formSave)) {
      return;
    }
    this.app.confirmMessage(null, () => {// on accepted
      this.positionService.saveOrUpdate(this.formSave.value)
      .subscribe(res => {
        if (this.positionService.requestIsSuccess(res)) {
          this.activeModal.close(res);
        }
      });
     }, () => {// on rejected
   });
  }
}
