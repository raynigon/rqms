import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AssertionTestEditorComponent } from './assertion-test-editor/assertion-test-editor.component';
import { AssertionTestsOverviewComponent } from './assertion-tests-overview/assertion-tests-overview.component';
import { RelevanceCaseEditorComponent } from './relevance-case-editor/relevance-case-editor.component';
import { RelevanceCasesOverviewComponent } from './relevance-cases-overview/relevance-cases-overview.component';
import { TestRunSchedulesEditorComponent } from './test-run-schedules-editor/test-run-schedules-editor.component';
import { TestRunSchedulesOverviewComponent } from './test-run-schedules-overview/test-run-schedules-overview.component';
import { TestRunsOverviewComponent } from './test-runs-overview/test-runs-overview.component';

const routes: Routes = [
  { path: 'assertion-tests', component: AssertionTestsOverviewComponent },
  { path: 'assertion-tests/:id', component: AssertionTestEditorComponent },
  { path: 'relevance-cases', component: RelevanceCasesOverviewComponent },
  { path: 'relevance-cases/:id', component: RelevanceCaseEditorComponent },
  { path: 'test-runs', component: TestRunsOverviewComponent },
  { path: 'test-run-schedules', component: TestRunSchedulesOverviewComponent },
  { path: 'test-run-schedules/:id', component: TestRunSchedulesEditorComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class OfflineRoutingModule { }
