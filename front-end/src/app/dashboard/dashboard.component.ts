import { Component, AfterViewInit, OnInit, ViewChild } from '@angular/core';
import { UIChart } from 'primeng/chart';
import { BaseComponent } from '../shared/components/base-component/base-component.component';

@Component({
	selector: 'app-dashboard',
	templateUrl: './dashboard.component.html',
	styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent extends BaseComponent implements AfterViewInit, OnInit {
	constructor(
	) {
		super(null);
	}

	public get f() {
		return this.formSearch.controls;
	}

	ngAfterViewInit() { }

	ngOnInit() {
		
	}
}
