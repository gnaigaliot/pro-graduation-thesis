import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { TimekeepingService } from 'src/app/core/services/timekeeping/timekeeping.service';
import { BaseComponent } from 'src/app/shared/components/base-component/base-component.component';
import { Storage } from 'src/app/shared/service/storage.service';

@Component({
  selector: 'app-timekeeping-search',
  templateUrl: './timekeeping-search.component.html',
  styleUrls: ['./timekeeping-search.component.css']
})
export class TimekeepingSearchComponent extends BaseComponent implements OnInit {
  formSearch: FormGroup;
  formConfig = {
    employeeName: [''],
    dateTimekeeping: [''],
    employeeId: [''],
    isAdmin: [false]
  };

  constructor(
    private timekeepingService: TimekeepingService
  ) {
    super(null);
    this.setMainService(timekeepingService);
    const userLogin = Storage.getUserToken();
    this.formSearch = this.buildForm({}, this.formConfig);
    this.formSearch.controls.employeeId.setValue(userLogin.employeeId);
    if (userLogin.lstRoleCode.includes('ROLE_ADMIN')) {
      this.formSearch.controls.isAdmin.setValue(true);
    }
  }

  ngOnInit(): void {
    this.processSearch();
  }

  // tslint:disable-next-line: typedef
  get f() {
    return this.formSearch.controls;
  }
}
