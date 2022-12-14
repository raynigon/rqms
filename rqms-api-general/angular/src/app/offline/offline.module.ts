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
import { AssertionTestsOverviewComponent } from './assertion-tests-overview/assertion-tests-overview.component';
import { AssertionTestEditorComponent } from './assertion-test-editor/assertion-test-editor.component';
import { SearchQueryEditorComponent } from './search-query-editor/search-query-editor.component';
import {MatListModule} from '@angular/material/list'; 

@NgModule({
  declarations: [
    AssertionTestEditorComponent,
    AssertionTestsOverviewComponent,
    LabelFilterComponent,
    LabelFilterEditorComponent,
    LabelListComponent,
    OfflineMetricSelectComponent,
    RelevanceCaseEditorComponent,
    RelevanceCasesOverviewComponent,
    SearchQueryEditorComponent,
    TestRunsOverviewComponent,
    TimeAgoPipe,
  ],
  imports: [
    CommonModule,
    DragDropModule,
    HttpClientModule,
    IntegrationModule,
    MatButtonModule,
    MatCardModule,
    MatChipsModule,
    MatDialogModule,
    MatListModule,
    MatExpansionModule,
    MatFormFieldModule,
    MatIconModule,
    MatInputModule,
    MatSelectModule,
    OfflineRoutingModule,
  ],
  bootstrap: [LabelFilterEditorComponent],
})
export class OfflineModule { }
