import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { OfflineRoutingModule } from './offline-routing.module';
import { RelevanceCasesOverviewComponent } from './relevance-cases-overview/relevance-cases-overview.component';
import { TagComponent } from './tag/tag.component';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { RelevanceCaseEditorComponent } from './relevance-case-editor/relevance-case-editor.component';


@NgModule({
  declarations: [
    RelevanceCasesOverviewComponent,
    RelevanceCaseEditorComponent,
    TagComponent
  ],
  imports: [
    MatIconModule,
    MatButtonModule,
    CommonModule,
    OfflineRoutingModule
  ]
})
export class OfflineModule { }
