import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HelperService } from 'src/app/shared/service/helper.service';
import { BasicService } from '../basic.service';

@Injectable({
  providedIn: 'root'
})
export class PositionService extends BasicService{

  constructor(
    public httpClient: HttpClient,
    public helperService: HelperService
  ) {
    super('ess', 'position', httpClient, helperService);
  }
}
