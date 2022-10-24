import {Component, OnInit} from '@angular/core';
import {LabelFilter} from '../model/label-filter';
import {RelevanceTestRunsClientService} from '../relevance-test-runs-client/relevance-test-runs-client.service';

@Component({
  selector: 'rqms-test-runs-overview',
  templateUrl: './test-runs-overview.component.html',
  styleUrls: ['./test-runs-overview.component.scss']
})
export class TestRunsOverviewComponent implements OnInit {

  public testRuns: any[] = [];
  public openedId: string | null = null;

  constructor(private client: RelevanceTestRunsClientService) {
  }

  ngOnInit() {
    this.reload()
  }

  async reload() {
    const result = await this.client.listTestRuns()
    this.testRuns = result;
  }

  async create() {
    const labelFilter: LabelFilter = {
      operator: "AND",
      conditions: [
        //{ comparison: "EQUALS", key: ".id", value: "5ff4221e-660c-485e-aedf-f9c88cb34c6b" }
        {comparison: "ALWAYS", key: null, value: null}
      ]
    };
    try {
      await this.client.createTestRun("default", labelFilter, "test");
      await this.reload();
    } catch (exception) {
      console.error(exception);
    }
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
