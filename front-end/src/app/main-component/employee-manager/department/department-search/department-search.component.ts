import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { DepartmentService } from 'src/app/core/services/department/department.service';
import { BaseComponent } from 'src/app/shared/components/base-component/base-component.component';
import { DepartmentFormComponent } from '../department-form/department-form.component';

@Component({
  selector: 'app-department-search',
  templateUrl: './department-search.component.html',
  styleUrls: ['./department-search.component.css']
})
export class DepartmentSearchComponent extends BaseComponent implements OnInit {
  formSearch: FormGroup;
  formConfig = {
    departmentCode: [''],
    departmentName: ['']
  };

  constructor(
    private modalService: NgbModal,
    private departmentService: DepartmentService
  ) {
    super(null);
    this.setMainService(departmentService);
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
      this.departmentService.findOne(item)
        .subscribe(res => {
          this.activeFormModal(this.modalService, DepartmentFormComponent, res.data);
        });
    } else {
      this.activeFormModal(this.modalService, DepartmentFormComponent, null);
    }
  }
}
