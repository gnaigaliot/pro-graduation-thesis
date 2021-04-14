import { Injectable } from "@angular/core";
import { BasicService } from "./basic.service";
import { HttpClient, HttpParams } from "@angular/common/http";
import { HelperService } from "../../shared/service/helper.service";
import { Observable } from "rxjs";
import { tap, catchError } from "rxjs/operators";
import { CommonUtils } from "../../shared/service/common-utils.service";

@Injectable({
  providedIn: "root"
})
export class EmployeeService extends BasicService {
  constructor(
    public httpClient: HttpClient,
    public helperService: HelperService
  ) {
    super("ess", "employees", httpClient, helperService);
  }

  public getEmployees(data?: any, event?: any): Observable<any> {
    if (!event) {
      this.credentials = Object.assign({}, data);
    }
    const searchData = CommonUtils.convertData(this.credentials);
    if (event) {
      searchData._search = event;
    }
    const buildParams = CommonUtils.buildParams(searchData);
    const url = `${this.serviceUrl}/search`;
    return this.getRequest(url, { params: buildParams });
  }
}
