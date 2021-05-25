import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HelperService } from '../../shared/service/helper.service';
import { BasicService } from './basic.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReportService extends BasicService {

  constructor(
    public httpClient: HttpClient,
    public helperService: HelperService
  ) {
    super('ess', 'report', httpClient, helperService);
  }

  public getEmployeeByAge(): Observable<any> {
    const url = `${this.serviceUrl}/get-employee-by-age`;
    return this.getRequest(url);
  }

  public getChart(): Observable<any> {
    const url = `${this.serviceUrl}/get-chart`;
    return this.getRequest(url);
  }
}
