import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RecommendRoutingModule } from './recommend-routing.module';
import { RecommendFormComponent } from './pages/recommend-form/recommend-form.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {ButtonModule} from 'primeng/button';
import {InputTextModule} from 'primeng/inputtext';
import { TableModule } from 'primeng/table';
import {RatingModule} from 'primeng/rating';


@NgModule({
  declarations: [RecommendFormComponent],
  imports: [
    CommonModule,
    RecommendRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    ButtonModule,
    InputTextModule,
    TableModule,
    RatingModule
  ]
})
export class RecommendModule { }
