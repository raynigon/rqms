import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { OnlineComponent } from './online.component';

const routes: Routes = [{ path: 'metrics', component: OnlineComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class OnlineRoutingModule { }
