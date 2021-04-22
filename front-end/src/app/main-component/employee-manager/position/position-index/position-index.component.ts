import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BaseComponent } from 'src/app/shared/components/base-component/base-component.component';

@Component({
  selector: 'app-position-index',
  templateUrl: './position-index.component.html',
  styleUrls: ['./position-index.component.css']
})
export class PositionIndexComponent extends BaseComponent implements OnInit {

  constructor(
    public actr: ActivatedRoute
  ) {
    super(actr);
  }

  ngOnInit(): void {
  }

}
