import {Component, OnInit} from '@angular/core';
import {RelevanceCaseListItem} from '../model/relevance-case';
import {RelevanceCasesClientService} from '../relevance-cases-client/relevance-cases-client.service';

const DATA_SOURCE: RelevanceCaseListItem[] = [
  {id: "1234567890", name: "Wasser", labels: {"category": "drink"}, resultCount: 12, relevanceScore: 1.0},
  {
    id: "1234567892",
    name: "Kokosnuss",
    labels: {"category": "fruit", "subcategory": "nut"},
    resultCount: 12,
    relevanceScore: 0.99999999999
  },
  {
    id: "1234567892",
    name: "Karotte",
    labels: {"category": "vegetables", "subcategory": "tuber"},
    resultCount: 12,
    relevanceScore: 0.99999999999
  },
  {
    id: "1234567892",
    name: "Karotte",
    labels: {"category": "vegetables", "subcategory": "tuber"},
    resultCount: 12,
    relevanceScore: 1.0
  },
  {
    id: "1234567891",
    name: "Milch",
    labels: {"meal": "breakfast", "category": "drink"},
    resultCount: 12,
    relevanceScore: 0.9
  },
  {
    id: "1234567892",
    name: "Karotte",
    labels: {"category": "vegetables", "subcategory": "tuber"},
    resultCount: 12,
    relevanceScore: 0.9
  },
  {
    id: "1234567892",
    name: "Karotte",
    labels: {"category": "vegetables", "subcategory": "tuber"},
    resultCount: 12,
    relevanceScore: 0.8
  },
  {
    id: "1234567892",
    name: "Apfelsaft",
    labels: {"category": "drink", "subcategory": "fruit"},
    resultCount: 12,
    relevanceScore: 0.75
  },
  {
    id: "1234567892",
    name: "Karotte",
    labels: {"category": "vegetables", "subcategory": "tuber"},
    resultCount: 12,
    relevanceScore: 0.7
  },
  {
    id: "1234567892",
    name: "Karotte",
    labels: {"category": "vegetables", "subcategory": "tuber"},
    resultCount: 12,
    relevanceScore: 0.6
  },
  {
    id: "1234567892",
    name: "Karotte",
    labels: {"category": "vegetables", "subcategory": "tuber"},
    resultCount: 12,
    relevanceScore: 0.5
  },
  {
    id: "1234567892",
    name: "Karotte",
    labels: {"category": "vegetables", "subcategory": "tuber"},
    resultCount: 12,
    relevanceScore: 0.4
  },
  {
    id: "1234567892",
    name: "Karotte",
    labels: {"category": "vegetables", "subcategory": "tuber"},
    resultCount: 12,
    relevanceScore: 0.3
  },
  {
    id: "1234567892",
    name: "Karotte",
    labels: {"category": "vegetables", "subcategory": "tuber"},
    resultCount: 12,
    relevanceScore: 0.2
  },
  {
    id: "1234567892",
    name: "Karotte",
    labels: {"category": "vegetables", "subcategory": "tuber"},
    resultCount: 12,
    relevanceScore: 0.1
  },
  {
    id: "1234567892",
    name: "Karotte",
    labels: {"category": "vegetables", "subcategory": "tuber"},
    resultCount: 12,
    relevanceScore: 0.0
  },
]


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
