import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CommonUtils } from 'src/app/shared/service/common-utils.service';
import { HelperService } from 'src/app/shared/service/helper.service';
import { BasicService } from '../basic.service';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService extends BasicService {

  constructor(
    public httpClient: HttpClient,
    public helperService: HelperService
  ) {
    super('ess', 'employee', httpClient, helperService);
  }

  public processExport(data?: any, event?: any): Observable<any> {
    if (!event) {
      this.credentials = Object.assign({}, data);
    }
    const searchData = CommonUtils.convertData(this.credentials);
    if (event) {
      searchData._search = event;
    }
    const buildParams = CommonUtils.buildParams(searchData);
    const url = `${this.serviceUrl}/export`;
    return this.getRequest(url, {params: buildParams, responseType: 'blob'});
  }

  public changeAvatar(data: any): Observable<any> {
    const url = `${this.serviceUrl}/change-avatar`;
    return this.postRequest(url, CommonUtils.convertData(data));
  }
}
