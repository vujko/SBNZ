import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MenuBarComponent } from './components/menu-bar/menu-bar.component'
import { MenubarModule } from 'primeng/menubar';
import {RouterModule} from '@angular/router';
import {ButtonModule} from 'primeng/button';
import {DialogModule} from 'primeng/dialog';


@NgModule({
  declarations: [
    MenuBarComponent
  ],
  imports: [
    CommonModule,
    MenubarModule,
    RouterModule,
    ButtonModule,
    DialogModule
  ],
  exports: [
    MenuBarComponent
  ]
})
export class SharedModule { }
