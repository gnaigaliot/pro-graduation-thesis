import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { TimekeepingService } from 'src/app/core/services/timekeeping/timekeeping.service';
import { BaseComponent } from 'src/app/shared/components/base-component/base-component.component';

@Component({
  selector: 'app-timekeeping-search',
  templateUrl: './timekeeping-search.component.html',
  styleUrls: ['./timekeeping-search.component.css']
})
export class TimekeepingSearchComponent extends BaseComponent implements OnInit {
  formSearch: FormGroup;
  formConfig = {
    employeeName: [''],
    dateTimekeeping: ['']
  };

  constructor(
    private timekeepingService: TimekeepingService
  ) {
    super(null);
    this.setMainService(timekeepingService);
    this.formSearch = this.buildForm({}, this.formConfig);
  }

  ngOnInit(): void {
    this.processSearch();
  }

  // tslint:disable-next-line: typedef
  get f() {
    return this.formSearch.controls;
  }
}
