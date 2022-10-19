import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ImprintComponent } from './imprint/imprint.component';

const routes: Routes = [
  { path: 'imprint', component: ImprintComponent },
  { path: 'offline', loadChildren: () => import('../offline/offline.module').then(m => m.OfflineModule) },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }