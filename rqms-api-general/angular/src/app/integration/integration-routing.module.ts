import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import { SearchEngineSelectComponent } from './search-engine-select/search-engine-select.component';

const routes: Routes = [
  { path: 'plugins', component: SearchEngineSelectComponent },
  { path: 'search-engines', component: SearchEngineSelectComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class IntegrationRoutingModule {
}
