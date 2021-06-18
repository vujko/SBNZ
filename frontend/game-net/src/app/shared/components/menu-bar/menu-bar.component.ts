import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { MenuItem } from 'primeng/api';
import { AuthenticationService } from '../../../modules/authentication/authentication.service'
import { LoginDTO } from 'src/app/modules/authentication/model';

@Component({
  selector: 'app-menu-bar',
  templateUrl: './menu-bar.component.html',
  styleUrls: ['./menu-bar.component.scss']
})
export class MenuBarComponent implements OnInit {

   constructor(private authenticationService: AuthenticationService,
                private router: Router) { }

    items: MenuItem[];
    authenticated: boolean;
    show: boolean;

    unauthorizedUserItems:MenuItem[] = [
        {
            label:'Log In',
            icon: 'pi pi-sign-in',
            routerLink: ['']
        }
    ];

    adminItems:MenuItem[] = [
    ]
    userItems:MenuItem[] = [
    ]

    ngOnInit() {
        this.show = true;
        this.authenticationService.currentUser.subscribe(user => this.update(user));
    }

    update(userInfo: LoginDTO) {
        if (!!userInfo) {
            this.authenticated = true;
            if (this.checkRole(userInfo, "ROLE_ADMIN")) {
              this.items = [
                ...this.adminItems
              ];
              this.router.navigate(['certificate/certificate-requests']);
            }
            else if(this.checkRole(userInfo, "ROLE_USER")) {
              this.items = [
                ...this.userItems
              ];
              this.router.navigate(['recommend']);
            }
          }
          else {
            this.authenticated = false;
            this.items = [
              ...this.unauthorizedUserItems
            ];
          }
    }

    checkRole(user: LoginDTO, expectedRole:string) {
        return user.roles.includes(expectedRole);
    }

    logout() {
        this.authenticationService.logout();
    }

}
