import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {IntegrationRoutingModule} from './integration-routing.module';
import {HttpClientModule} from '@angular/common/http';
import {SearchEngineClientService} from './search-engine-client/search-engine-client.service';
import { SearchEngineSelectComponent } from './search-engine-select/search-engine-select.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { SearchQuerySelectComponent } from './search-query-select/search-query-select.component';
import { SearchQueryClientService } from './search-query-client/search-query-client.service';


@NgModule({
  declarations: [
    SearchEngineSelectComponent,
    SearchQuerySelectComponent
  ],
  exports: [
    SearchEngineSelectComponent,
    SearchQuerySelectComponent
  ],
  imports: [
    CommonModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    HttpClientModule,
    IntegrationRoutingModule
  ],
  providers: [
    SearchEngineClientService,
    SearchQueryClientService
  ]
})
export class IntegrationModule {
}
