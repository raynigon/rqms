import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RelevanceCaseEditorComponent } from './relevance-case-editor/relevance-case-editor.component';
import { RelevanceCasesOverviewComponent } from './relevance-cases-overview/relevance-cases-overview.component';

const routes: Routes = [
  { path: 'relevance-cases', component: RelevanceCasesOverviewComponent },
  { path: 'relevance-cases/:id', component: RelevanceCaseEditorComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class OfflineRoutingModule { }
