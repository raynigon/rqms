import {Component, OnInit} from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { LabelFilterEditorComponent } from '../label-filter-editor/label-filter-editor.component';
import {LabelFilter} from '../model/label-filter';
import {RelevanceTestRunsClientService} from '../relevance-test-runs-client/relevance-test-runs-client.service';

@Component({
  selector: 'rqms-test-runs-overview',
  templateUrl: './test-runs-overview.component.html',
  styleUrls: ['./test-runs-overview.component.scss']
})
export class TestRunsOverviewComponent implements OnInit {

  public description: string = ""
  public searchEngine: string = "default"
  public labelFilter: LabelFilter = {operator: "AND", conditions: []}
  public testRuns: any[] = [];
  public openedId: string | null = null;

  constructor(
    private dialog: MatDialog,
    private client: RelevanceTestRunsClientService
    ) {
  }

  ngOnInit() {
    this.reload()
  }

  async reload() {
    const result = await this.client.listTestRuns()
    this.testRuns = result;
  }

  async create() {
    try {
      await this.client.createTestRun(this.searchEngine, this.labelFilter, this.description);
      await this.reload();
    } catch (exception) {
      console.error(exception);
    }
  }

  editLabelFilters(){
    let dialogRef = this.dialog.open(LabelFilterEditorComponent, {
      height: '600px',
      width: '800px',
      data: JSON.parse(JSON.stringify(this.labelFilter))
    });
    dialogRef.afterClosed().subscribe((result)=>{
      this.labelFilter = result;
    });
  }

  scoreToColor(score: number, type: "background" | "text"): string {
    let red = (1 - Math.pow(score, 2));
    let green = (1 - Math.pow(score - 1, 2));
    let normalizer = 1 / (red + green);
    red = Math.min(red / normalizer, 1.0) * 255;
    green = Math.min(green / normalizer, 1.0) * 200;
    return type == "background" ? `rgb(${red}, ${green}, 0)` : "rgb(255, 255, 255)"
  }

}
