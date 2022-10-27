import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {OfflineRoutingModule} from './offline-routing.module';
import {RelevanceCasesOverviewComponent} from './relevance-cases-overview/relevance-cases-overview.component';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {RelevanceCaseEditorComponent} from './relevance-case-editor/relevance-case-editor.component';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {HttpClientModule} from '@angular/common/http';
import {MatChipsModule} from '@angular/material/chips';
import {MatSelectModule} from '@angular/material/select';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatCardModule} from '@angular/material/card';
import {DragDropModule} from '@angular/cdk/drag-drop';
import {LabelListComponent} from './label-list/label-list.component';
import {TestRunsOverviewComponent} from './test-runs-overview/test-runs-overview.component';
import {TimeAgoPipe} from './pipes/time-ago-pipe';
import { LabelFilterComponent } from './label-filter/label-filter.component';
import {MatDialogModule} from '@angular/material/dialog'; 
import { LabelFilterEditorComponent } from './label-filter-editor/label-filter-editor.component';
import { IntegrationModule } from '../integration/integration.module';
import { OfflineMetricSelectComponent } from './offline-metric-select/offline-metric-select.component';

@NgModule({
  declarations: [
    OfflineMetricSelectComponent,
    RelevanceCasesOverviewComponent,
    RelevanceCaseEditorComponent,
    TestRunsOverviewComponent,
    LabelListComponent,
    LabelFilterComponent,
    LabelFilterEditorComponent,
    TimeAgoPipe
  ],
  imports: [
    IntegrationModule,
    MatDialogModule,
    DragDropModule,
    MatCardModule,
    MatExpansionModule,
    MatSelectModule,
    MatChipsModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    MatButtonModule,
    CommonModule,
    HttpClientModule,
    OfflineRoutingModule,
  ],
  bootstrap: [LabelFilterEditorComponent],
})
export class OfflineModule { }
