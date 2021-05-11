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
}
