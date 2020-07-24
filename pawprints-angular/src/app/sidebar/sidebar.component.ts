import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../_services';
import { User } from '../_models';

declare interface RouteInfo {
  path: string;
  title: string;
  rtlTitle: string;
  icon: string;
  class: string;
}
export const ROUTES: RouteInfo[] = [
  {
    path: '/orders',
    title: 'Admin',
    rtlTitle: 'إخطارات',
    icon: 'icon-settings-gear-63',
    class: '',
  },
  {
    path: '/shop',
    title: 'Shop',
    rtlTitle: 'إخطارات',
    icon: 'icon-tag',
    class: '',
  },
  {
    path: '/helath',
    title: 'Health',
    rtlTitle: 'إخطارات',
    icon: 'icon-heart-2',
    class: '',
  },
  {
    path: '/linage',
    title: 'Linage',
    rtlTitle: 'إخطارات',
    icon: 'icon-globe-2',
    class: '',
  },
  {
    path: '/cart',
    title: 'Cart',
    rtlTitle: 'إخطارات',
    icon: 'icon-cart',
    class: '',
  },
  {
    path: '/client/comments',
    title: 'Comments',
    rtlTitle: 'إخطارات',
    icon: 'icon-chat-33',
    class: '',
  },
];

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss'],
})
export class SidebarComponent implements OnInit {
  menuItems: any[];
  isClicked = true;
  user: User;
  isAdmin: boolean;

  constructor(
    private router: Router,
    private authenticationService: AuthenticationService
  ) {}

  ngOnInit() {
    this.menuItems = ROUTES.filter((menuItem) => menuItem);

    this.user = this.authenticationService.userValue;
    if (this.user !== null) {
      this.isAdmin =
        this.user.roles.find((role) => role.role === 'ROLE_ADMIN') !==
        undefined;
      console.log(this.isAdmin);
    }
  }

  isMobileMenu() {
    if (window.innerWidth > 991) {
      return false;
    }
    return true;
  }

  navigate(url) {
    this.router.navigate([url]);
  }
}
