import { Component, AfterViewInit, OnInit, ViewChild } from '@angular/core';
import { UIChart } from 'primeng/chart';
import { BaseComponent } from '../shared/components/base-component/base-component.component';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})

export class DashboardComponent extends BaseComponent implements AfterViewInit, OnInit {
  formConfig = {};
  data: any;
  constructor() {
    super(null);
    this.formSearch = this.buildForm({}, this.formConfig);
    this.data = {
      labels: ['A', 'B', 'C'],
      datasets: [
        {
          data: [300, 50, 100],
           backgroundColor: ['#FF6384', '#36A2EB', '#FFCE56'],
           hoverBackgroundColor: ['#FF6384', '#36A2EB', '#FFCE56']
        }]
    };
  }

  // tslint:disable-next-line:typedef
  get f() {
    return this.formSearch.controls;
  }

  ngAfterViewInit(): void { }

  ngOnInit(): void {}
}
