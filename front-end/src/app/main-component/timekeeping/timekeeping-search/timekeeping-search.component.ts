import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { TimekeepingService } from 'src/app/core/services/timekeeping/timekeeping.service';
import { BaseComponent } from 'src/app/shared/components/base-component/base-component.component';
import { Storage } from 'src/app/shared/service/storage.service';
import { saveAs } from 'file-saver';
import { DepartmentService } from 'src/app/core/services/department/department.service';
import { EmployeeService } from 'src/app/core/services/employee/employee.service';

@Component({
  selector: 'app-timekeeping-search',
  templateUrl: './timekeeping-search.component.html',
  styleUrls: ['./timekeeping-search.component.css']
})
export class TimekeepingSearchComponent extends BaseComponent implements OnInit {
  formSearch: FormGroup;
  public listDepartment: any = [];
  public listEmployee: any = [];
  formConfig = {
    employeeName: [''],
    dateTimekeeping: [''],
    employeeId: [''],
    isAdmin: [false],
    departmentId: ['']
  };
  listDataExport: string[] = ['dateTimekeeping', 'employeeName', 'arrivalTime', 'departureTime', 'isLate', 'leftEarly', 'arrivalLateTime', 'departureEarlyTime'];

  constructor(
    private timekeepingService: TimekeepingService,
    private departmentService: DepartmentService,
    private employeeService: EmployeeService,
  ) {
    super(null);
    this.setMainService(timekeepingService);
    const userLogin = Storage.getUserToken();
    this.formSearch = this.buildForm({}, this.formConfig);
    if (userLogin.lstRoleCode.includes('ROLE_ADMIN')) {
      this.formSearch.controls.isAdmin.setValue(true);
    } else {
      this.formSearch.controls.employeeId.setValue(userLogin.employeeId);
    }
    this.departmentService.getAllWithoutPagination().subscribe(res => {
      this.listDepartment = res;
    });
    this.employeeService.getListActiveEmployee().subscribe(res => {
      res.forEach(element => {
        element['employeeNameCode'] = `${element.employeeName} (${element.employeeCode})`;
      });
      this.listEmployee = res;
    });
  }

  ngOnInit(): void {
    this.processSearch();
  }

  // tslint:disable-next-line: typedef
  get f() {
    return this.formSearch.controls;
  }

  exportExcel() {
    const timekeepingExport: any = [];
    this.resultList.data.forEach(element => {
      const obj = new Object();
      this.listDataExport.forEach(key => {
        if (key === 'dateTimekeeping') {
          var date = new Date(element[key]);
          obj[key] = ((date.getMonth() > 8) ? (date.getMonth() + 1) : ('0' + (date.getMonth() + 1))) + '/' + ((date.getDate() > 9) ? date.getDate() : ('0' + date.getDate())) + '/' + date.getFullYear();
        } else if (key === 'isLate') {
          obj[key] = element[key] == 1 ? 'Có' : '';
        } else if (key === 'leftEarly') {
          obj[key] = element[key] == 1 ? 'Có' : '';
        } else {
          obj[key] = element[key];
        }
      });
      timekeepingExport.push(obj);
    });

    import("xlsx").then(xlsx => {
      const worksheet = xlsx.utils.json_to_sheet(timekeepingExport);
      
      const workbook = { Sheets: { 'data': worksheet }, SheetNames: ['data'] };
      const excelBuffer: any = xlsx.write(workbook, { bookType: 'xlsx', type: 'array' });
      this.saveAsExcelFile(excelBuffer, "attendances");
    });
  }

  saveAsExcelFile(buffer: any, fileName: string): void {
      let EXCEL_TYPE = 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8';
      let EXCEL_EXTENSION = '.xlsx';
      const data: Blob = new Blob([buffer], {
          type: EXCEL_TYPE
      });
      saveAs(data, fileName + '_export_' + new Date().getTime() + EXCEL_EXTENSION);
  }
}
