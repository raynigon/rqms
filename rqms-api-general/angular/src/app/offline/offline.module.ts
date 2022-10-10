import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { OfflineRoutingModule } from './offline-routing.module';
import { JudgmentListsOverviewComponent } from './judgment-lists-overview/judgment-lists-overview.component';
import { TagComponent } from './tag/tag.component';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { JudgmentListEditorComponent } from './judgment-list-editor/judgment-list-editor.component';


@NgModule({
  declarations: [
    JudgmentListsOverviewComponent,
    JudgmentListEditorComponent,
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
