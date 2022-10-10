import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { JudgmentListEditorComponent } from './judgment-list-editor/judgment-list-editor.component';
import { JudgmentListsOverviewComponent } from './judgment-lists-overview/judgment-lists-overview.component';

const routes: Routes = [
  { path: 'judgment-lists', component: JudgmentListsOverviewComponent },
  { path: 'judgment-lists/:id', component: JudgmentListEditorComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class OfflineRoutingModule { }
