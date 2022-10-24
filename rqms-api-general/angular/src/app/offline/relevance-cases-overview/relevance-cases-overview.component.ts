import {Component, OnInit} from '@angular/core';
import {RelevanceCaseListItem} from '../model/relevance-case';
import {RelevanceCasesClientService} from '../relevance-cases-client/relevance-cases-client.service';


@Component({
  selector: 'rqms-relevance-cases-overview',
  templateUrl: './relevance-cases-overview.component.html',
  styleUrls: ['./relevance-cases-overview.component.scss']
})
export class RelevanceCasesOverviewComponent implements OnInit {

  public cases: RelevanceCaseListItem[] = [];
  private original: RelevanceCaseListItem[] = [];

  constructor(private client: RelevanceCasesClientService) {
  }

  ngOnInit(): void {
    this.client.listRelevanceCases()
      .then(result => {
        this.cases = result;
        this.original = result
      })
      .catch(console.error);
  }

  getLabels(relevanceCase: RelevanceCaseListItem): { key: string, value: string }[] {
    return Object.entries(relevanceCase.labels)
      .map(([key, value]) => {
        return {"key": key, "value": value}
      });
  }

  filter(target: EventTarget | null) {
    if (target === null || !('value' in target)) {
      return
    }
    const filter = (target as HTMLInputElement).value.toLowerCase();
    if (filter === null || filter === "") {
      this.cases = this.original
      return
    }
    this.cases = this.original.filter((item: RelevanceCaseListItem) => {
      if (item.name.toLowerCase().includes(filter)) {
        return true;
      }
      for (const [key, value] of Object.entries(item.labels)) {
        if (key.toLowerCase().includes(filter) || value.toLowerCase().includes(filter)) {
          return true
        }
      }
      return false;
    })
  }

  scoreToColor(score: number, type: "background" | "text"): string {

    let red = (1 - Math.pow(score, 2));
    let green = (1 - Math.pow(score - 1, 2));
    let normalizer = 1/(red + green);
    red = Math.min(red / normalizer, 1.0)  * 255;
    green = Math.min(green / normalizer, 1.0) * 200;

    return type == "background" ? `rgb(${red}, ${green}, 0)` : "rgb(255, 255, 255)"
  }
}
