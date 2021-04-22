import {
  ChangeDetectorRef,
  Component,
  NgZone,
  OnDestroy,
  ViewChild,
  HostListener,
  Directive,
  AfterViewInit,
  OnInit
} from '@angular/core';
import { MediaMatcher } from '@angular/cdk/layout';
import { MenuItems } from '../../../shared/menu-items/menu-items';
import { Storage } from '../../../shared/service/storage.service';
import { UserToken } from '../../../core/models/user-token.model';
import { Router } from '@angular/router';
import { MenuItem } from 'primeng/api';
import { BaseComponent } from '../../../shared/components/base-component/base-component.component';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: []
})
export class AppSidebarComponent extends BaseComponent implements OnInit, OnDestroy {
  mobileQuery: MediaQueryList;
  userInfo: UserToken;

  items: MenuItem[];
  private mobileQueryListener: () => void;

  constructor(
    changeDetectorRef: ChangeDetectorRef,
    media: MediaMatcher,
    public router: Router,
    public menuItems: MenuItems
  ) {
    super(null);
    this.mobileQuery = media.matchMedia('(min-width: 768px)');
    this.mobileQueryListener = () => changeDetectorRef.detectChanges();
    // tslint:disable-next-line: deprecation
    this.mobileQuery.addListener(this.mobileQueryListener);
    this.userInfo = Storage.getUserToken();
  }

  ngOnInit(): void {
    this.innitMenu();
  }

  ngOnDestroy(): void {
    // tslint:disable-next-line: deprecation
    this.mobileQuery.removeListener(this.mobileQueryListener);
  }

  // tslint:disable-next-line: typedef
  public userInformation() {
    this.router.navigate(['/user-info']);
  }

  // tslint:disable-next-line: typedef
  public changePassword() {
    this.router.navigate(['/user-info/change-password']);
  }

  // tslint:disable-next-line: typedef
  public logOut() {
    this.router.navigate(['/login']);
    Storage.clear();
  }
  // tslint:disable-next-line: member-ordering
  routerLink: ['/pagename'];
  // tslint:disable-next-line: typedef
  private innitMenu() {
    // TODO Khai báo các url trên side-sidebar
    this.items = [
      {
        label: 'Trang chủ',
        icon: 'pi pi-home',
        routerLink: ['/dashboard']
      },
      {
        label: 'Quản lý user',
        icon: 'pi pi-user',
        routerLink: ['/user']
      },
      {
        label: 'Quản lý nhân viên',
        icon: 'pi pi-users',
        items: [
          {
            label: 'Quản lý phòng ban',
            icon: 'pi pi-th-large',
            routerLink: ['/employee-manager/departments']
          },
          {
            label: 'Quản lý nhân viên',
            icon: 'pi pi-th-large',
            routerLink: ['/employee-manager/employees']
          },
          {
            label: 'Quản lý chức vụ',
            icon: 'pi pi-th-large',
            routerLink: ['/employee-manager/positions']
          }
        ]
      },
      {
        label: 'Quản lý chấm công',
        icon: 'pi pi-chart-line',
        routerLink: ['/timekeeping-manager']
      }
    ];
  }
}
