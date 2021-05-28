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
    const url = `${this.serviceUrl}/get-pie-chart`;
    return this.getRequest(url);
  }

  public getChart(firstDate: Date, lastDate: Date): Observable<any> {
    const url = `${this.serviceUrl}/get-chart/${firstDate}/${lastDate}`;
    return this.getRequest(url);
  }

  public getEvents(): Observable<any> {
    const url = `${this.serviceUrl}/get-event`;
    return this.getRequest(url);
  }
}
