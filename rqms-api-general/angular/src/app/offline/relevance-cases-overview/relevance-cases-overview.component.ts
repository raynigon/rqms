import { Component, OnInit } from '@angular/core';
import { JudgmentList } from '../model/relevance-case';

@Component({
  selector: 'rqms-relevance-cases-overview',
  templateUrl: './relevance-cases-overview.component.html',
  styleUrls: ['./relevance-cases-overview.component.scss']
})
export class RelevanceCasesOverviewComponent implements OnInit {

  public lists: JudgmentList[] = [
    { id: "1234567890", title: "Wasser", tags: ["category:drink"], resultCount: 12, testScore: 1.0 },
    { id: "1234567892", title: "Kokosnuss", tags: ["category:fruit", "subcategory:nut"], resultCount: 12, testScore: 0.99999999999 },
    { id: "1234567892", title: "Karotte", tags: ["category:vegetables", "subcategory:tuber"], resultCount: 12, testScore: 0.99999999999 },
    { id: "1234567892", title: "Karotte", tags: ["category:vegetables", "subcategory:tuber"], resultCount: 12, testScore: 1.0 },
    { id: "1234567891", title: "Milch", tags: ["meal:breakfast", "category:drink"], resultCount: 12, testScore: 0.9 },
    { id: "1234567892", title: "Karotte", tags: ["category:vegetables", "subcategory:tuber"], resultCount: 12, testScore: 0.9 },
    { id: "1234567892", title: "Karotte", tags: ["category:vegetables", "subcategory:tuber"], resultCount: 12, testScore: 0.8 },
    { id: "1234567892", title: "Saft", tags: ["category:drink", "category:fruit"], resultCount: 12, testScore: 0.75 },
    { id: "1234567892", title: "Karotte", tags: ["category:vegetables", "subcategory:tuber"], resultCount: 12, testScore: 0.7 },
    { id: "1234567892", title: "Karotte", tags: ["category:vegetables", "subcategory:tuber"], resultCount: 12, testScore: 0.6 },
    { id: "1234567892", title: "Karotte", tags: ["category:vegetables", "subcategory:tuber"], resultCount: 12, testScore: 0.5 },
    { id: "1234567892", title: "Karotte", tags: ["category:vegetables", "subcategory:tuber"], resultCount: 12, testScore: 0.4 },
    { id: "1234567892", title: "Karotte", tags: ["category:vegetables", "subcategory:tuber"], resultCount: 12, testScore: 0.3 },
    { id: "1234567892", title: "Karotte", tags: ["category:vegetables", "subcategory:tuber"], resultCount: 12, testScore: 0.2 },
    { id: "1234567892", title: "Karotte", tags: ["category:vegetables", "subcategory:tuber"], resultCount: 12, testScore: 0.1 },
    { id: "1234567892", title: "Karotte", tags: ["category:vegetables", "subcategory:tuber"], resultCount: 12, testScore: 0.0 },
  ]

  constructor() { }

  ngOnInit(): void {
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
