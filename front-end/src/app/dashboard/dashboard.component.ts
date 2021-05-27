import { Component, AfterViewInit, OnInit, ViewChild } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { UIChart } from 'primeng/chart';
import { ReportService } from '../core/services/report.service';
import { BaseComponent } from '../shared/components/base-component/base-component.component';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid';
import interactionPlugin from '@fullcalendar/interaction'
import { EventService } from '../core/services/eventservice';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})

export class DashboardComponent extends BaseComponent implements AfterViewInit, OnInit {
  formSearch: FormGroup;
  formConfig = {
    dateInWeek: [new Date()]
  };
  data: any;  // pie chart
  basicData: any;   // line chart
  basicOptions: any;
  rangeDates: Date[];
  // data line chart
  totalTimekeeping: any[];
  totalArrivalLate: any[];
  totalLeftEarly: any[];
  fromDate: Date = this.getMonday(new Date());
  toDate: Date = this.getSunday(new Date());

  events: any[];
  options: any;

  constructor(
    public reportService: ReportService,
    private eventService: EventService
  ) {
    super(null);
    this.formSearch = this.buildForm({}, this.formConfig);
    this.initLineChart();
    this.initPieChart();
  }

  // tslint:disable-next-line:typedef
  get f() {
    return this.formSearch.controls;
  }

  ngAfterViewInit(): void { }

  ngOnInit(): void {
    const tempTime = new Date();
    this.loadLineChart(tempTime);
    this.loadPieChart();
    // event full-calendar
    this.eventService.getEvents().then(events => {this.events = events;});
    this.options = {
      plugins: [dayGridPlugin, timeGridPlugin, interactionPlugin],
      defaultDate: new Date(),
      header: {
          left: 'prev,next',
          center: 'title',
          right: 'dayGridMonth,timeGridWeek,timeGridDay'
      },
      editable: true
    };
  }

  getMonday(d: Date) {
    const date = new Date(d);
    const day = d.getDay();
    const diff = d.getDate() - day + (day == 0 ? -6:1); // adjust when day is sunday
    return new Date(d.setDate(diff));
  }

  getSunday(d: Date) {
    const date = new Date(d);
    const day = d.getDay();
    const diff = d.getDate() - day + 7; // adjust when day is sunday
    return new Date(d.setDate(diff));
  }

  changeDate() {
    const time = new Date(this.formSearch.controls.dateInWeek.value);
    this.fromDate = this.getMonday(time);
    this.toDate = this.getSunday(time);
    this.loadLineChart(time);
  }

  loadLineChart(timeCheck: Date): void {
    this.reportService.getChart(this.getMonday(timeCheck), this.getSunday(timeCheck)).subscribe(res => {
      // line chart
      this.basicData = {
        labels: ['Thứ 2', 'Thứ 3', 'Thứ 4', 'Thứ 5', 'Thứ 6', 'Thứ 7', 'Chủ nhật'],
        datasets: [
            {
                label: 'Tổng số nhân viên chấm công',
                data: res.data.listAttendance,
                fill: false,
                borderColor: '#42A5F5'
            },
            {
                label: 'Tổng số nhân viên đến muộn',
                data: res.data.listChecinLate,
                fill: false,
                borderColor: '#FFA726'
            },
            {
              label: 'Tổng số nhân viên về sớm',
              data: res.data.listDepartureEarly,
              fill: false,
              borderColor: '#FF6384'
          }
        ]
      }
    });
  }

  initLineChart(): void {
    // line chart
    this.basicData = {
      labels: ['Thứ 2', 'Thứ 3', 'Thứ 4', 'Thứ 5', 'Thứ 6', 'Thứ 7', 'Chủ nhật'],
      datasets: [
          {
              label: 'Tổng số nhân viên chấm công',
              data: [],
              fill: false,
              borderColor: '#42A5F5'
          },
          {
              label: 'Tổng số nhân viên đến muộn',
              data: [],
              fill: false,
              borderColor: '#FFA726'
          },
          {
            label: 'Tổng số nhân viên về sớm',
            data: [],
            fill: false,
            borderColor: '#FF6384'
        }
      ]
    }
  }

  initPieChart(): void {
    // pie chart
    this.data = {
      labels: ['Dưới 18 tuổi', '18 - 30 tuổi', '31 - 40 tuổi', '41 - 50 tuổi' , 'Trên 50 tuổi'],
      datasets: [
        {
          data: [],
          backgroundColor: ['#00dbd8', '#FF6384', '#36A2EB', '#FFCE56', '#52d952'],
          hoverBackgroundColor: ['#00dbd8', '#FF6384', '#36A2EB', '#FFCE56', '#52d952']
        }]
    };
  }

  loadPieChart(): void {
    this.reportService.getEmployeeByAge().subscribe(res => {
      this.data = {
        labels: ['Dưới 18 tuổi', '18 - 30 tuổi', '31 - 40 tuổi', '41 - 50 tuổi' , 'Trên 50 tuổi'],
        datasets: [
          {
            data: res.data.listDataPieChart,
            backgroundColor: ['#00dbd8', '#FF6384', '#36A2EB', '#FFCE56', '#52d952'],
            hoverBackgroundColor: ['#00dbd8', '#FF6384', '#36A2EB', '#FFCE56', '#52d952']
          }]
      };
    })
  }
}
