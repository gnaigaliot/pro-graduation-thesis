import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CommonUtils } from 'src/app/shared/service/common-utils.service';
import { HelperService } from 'src/app/shared/service/helper.service';
import { BasicService } from '../basic.service';

@Injectable({
  providedIn: 'root'
})
export class TimekeepingService extends BasicService {

  constructor(
    public helperService: HelperService,
    public httpClient: HttpClient
  ) {
    super('ess', 'timekeeping', httpClient, helperService);
  }

  public processExport(data: any): Observable<any> {
    const buildParams = CommonUtils.buildParams(data);
    const url = `${this.serviceUrl}/process-export`;
    return this.getRequest(url, {params: buildParams, responseType: 'blob'});
  }
}
