import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HelperService } from 'src/app/shared/service/helper.service';
import { BasicService } from '../basic.service';

@Injectable({
  providedIn: 'root'
})
export class GenCodeService extends BasicService {

  constructor(
    public httpClient: HttpClient,
    public helperService: HelperService
  ) {
    super('ess', 'genCode', httpClient, helperService)
  }

  public getLastCodeNumberEmployee(): Observable<any> {
    const url = `${this.serviceUrl}/last-code`;
    return this.getRequest(url);
  }
}
