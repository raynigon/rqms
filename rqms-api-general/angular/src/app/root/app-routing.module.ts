import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ImprintComponent} from './imprint/imprint.component';

const routes: Routes = [
  {path: 'imprint', component: ImprintComponent},
  {path: 'offline', loadChildren: () => import('../offline/offline.module').then(m => m.OfflineModule)},
  {path: 'integration', loadChildren: () => import('../integration/integration.module').then(m => m.IntegrationModule)},
  { path: 'online', loadChildren: () => import('../online/online.module').then(m => m.OnlineModule) },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
