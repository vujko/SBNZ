import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { NotificationRoutingModule } from './notification-routing.module';
import { PagesComponent } from './pages/pages.component';
import { TableModule } from 'primeng/table';
import { CardModule } from 'primeng/card';


@NgModule({
  declarations: [PagesComponent],
  imports: [
    CommonModule,
    NotificationRoutingModule,
    TableModule,
    CardModule
  ]
})
export class NotificationModule { }
