import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'auth',
    pathMatch: 'full',
  },

  {
    path: 'auth',
    loadChildren: () => import('./modules/authentication/authentication.module').then(m => m.AuthenticationModule)
  },

  {
    path: 'recommend',
    loadChildren: () => import('./modules/recommend/recommend.module').then(m => m.RecommendModule)
  },

  {
    path: 'notification',
    loadChildren: () => import('./modules/notification/notification.module').then(m => m.NotificationModule)
  },

  {
    path: 'report',
    loadChildren: () => import('./modules/report/report.module').then(m => m.ReportModule)
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
