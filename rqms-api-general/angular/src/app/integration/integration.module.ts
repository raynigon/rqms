import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {IntegrationRoutingModule} from './integration-routing.module';
import {HttpClientModule} from '@angular/common/http';
import {SearchEngineClientService} from './search-engine-client/search-engine-client.service';


@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    HttpClientModule,
    IntegrationRoutingModule
  ],
  providers: [
    SearchEngineClientService
  ]
})
export class IntegrationModule {
}
