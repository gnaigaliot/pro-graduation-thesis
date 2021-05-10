import { Component, OnInit } from '@angular/core';
import { BaseComponent } from 'src/app/shared/components/base-component/base-component.component';

@Component({
  selector: 'app-timekeeping-index',
  templateUrl: './timekeeping-index.component.html',
  styleUrls: ['./timekeeping-index.component.css']
})
export class TimekeepingIndexComponent extends BaseComponent implements OnInit {

  constructor() {
    super(null);
  }

  ngOnInit(): void {
  }

}
