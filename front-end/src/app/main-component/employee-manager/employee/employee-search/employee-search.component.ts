import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { EmployeeService } from 'src/app/core/services/employee/employee.service';
import { BaseComponent } from 'src/app/shared/components/base-component/base-component.component';
import { EmployeeFormComponent } from '../employee-form/employee-form.component';

@Component({
  selector: 'app-employee-search',
  templateUrl: './employee-search.component.html',
  styleUrls: ['./employee-search.component.css']
})
export class EmployeeSearchComponent extends BaseComponent implements OnInit {
  formSearch: FormGroup;
  formConfig = {
    employeeCode: [''],
    employeeName: [''],
    gender: [''],
    email: ['']
  };
  constructor(
    private modalService: NgbModal,
    private employeeService: EmployeeService
  ) {
    super(null);
    this.setMainService(employeeService);
    this.formSearch = this.buildForm({}, this.formConfig);
  }

  ngOnInit(): void {
    this.processSearch();
  }

  get f() {
    return this.formSearch.controls;
  }

  public prepareSaveOrUpdate(item?: any): void {
    if (item && item > 0) {
      this.employeeService.findOne(item)
        .subscribe(res => {
          this.activeFormModal(this.modalService, EmployeeFormComponent, res.data);
        });
    } else {
      this.activeFormModal(this.modalService, EmployeeFormComponent, null);
    }
  }
}
