import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ReportRoutingModule } from './report-routing.module';
import { ReportComponent } from './pages/report/report.component';
import { TableModule } from 'primeng/table';
import { CardModule } from 'primeng/card';
import {RatingModule} from 'primeng/rating';

@NgModule({
  declarations: [ReportComponent],
  imports: [
    CommonModule,
    ReportRoutingModule,
    TableModule,
    CardModule,
    RatingModule
  ]
})
export class ReportModule { }
