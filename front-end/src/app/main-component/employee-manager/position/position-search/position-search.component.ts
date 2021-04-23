import { Component, OnInit } from '@angular/core';
import { FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { PositionService } from 'src/app/core/services/position/position.service';
import { BaseComponent } from 'src/app/shared/components/base-component/base-component.component';
import { PositionFormComponent } from '../position-form/position-form.component';

@Component({
  selector: 'app-position-search',
  templateUrl: './position-search.component.html',
  styleUrls: ['./position-search.component.css']
})
export class PositionSearchComponent extends BaseComponent implements OnInit {
  formSearch: FormGroup;
  formConfig = {
    positionCode: ['', [Validators.maxLength(50)]],
    positionName: ['', [Validators.maxLength(50)]],
    salary: ['', [Validators.maxLength(50)]]
  };
  constructor(
    public actr: ActivatedRoute,
    private positionService: PositionService,
    private modalService: NgbModal
  ) {
    super(null);
    this.setMainService(this.positionService);
    this.formSearch = this.buildForm({}, this.formConfig);
  }

  ngOnInit(): void {
    this.processSearch();
  }

  // tslint:disable-next-line: typedef
  public get f() {
    return this.formSearch.controls;
  }

  public prepareSaveOrUpdate(item?: any): void {
    if (item && item > 0) {
      this.positionService.findOne(item)
        .subscribe(res => {
          this.activeFormModal(this.modalService, PositionFormComponent, res.data);
        });
    } else {
      this.activeFormModal(this.modalService, PositionFormComponent, null);
    }
  }
}
