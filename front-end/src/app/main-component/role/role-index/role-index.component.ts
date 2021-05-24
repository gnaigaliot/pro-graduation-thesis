import { Component, OnInit } from '@angular/core';
import { BaseComponent } from 'src/app/shared/components/base-component/base-component.component';

@Component({
  selector: 'app-role-index',
  templateUrl: './role-index.component.html',
  styleUrls: ['./role-index.component.css']
})
export class RoleIndexComponent extends BaseComponent implements OnInit {

  constructor() {
    super(null);
  }

  ngOnInit(): void {
  }

}
