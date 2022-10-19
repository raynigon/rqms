import { Component, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { RelevanceCase } from '../model/relevance-case';

const DATA_SOURCE: RelevanceCase[] = [
  { id: "1234567890", title: "Wasser", tags: {"category": "drink"}, resultCount: 12, relevanceScore: 1.0 },
  { id: "1234567892", title: "Kokosnuss", tags: {"category": "fruit", "subcategory": "nut"}, resultCount: 12, relevanceScore: 0.99999999999 },
  { id: "1234567892", title: "Karotte", tags: {"category": "vegetables", "subcategory": "tuber"}, resultCount: 12, relevanceScore: 0.99999999999 },
  { id: "1234567892", title: "Karotte", tags: {"category": "vegetables", "subcategory": "tuber"}, resultCount: 12, relevanceScore: 1.0 },
  { id: "1234567891", title: "Milch", tags: {"meal": "breakfast", "category": "drink"}, resultCount: 12, relevanceScore: 0.9 },
  { id: "1234567892", title: "Karotte", tags: {"category": "vegetables", "subcategory": "tuber"}, resultCount: 12, relevanceScore: 0.9 },
  { id: "1234567892", title: "Karotte", tags: {"category": "vegetables", "subcategory": "tuber"}, resultCount: 12, relevanceScore: 0.8 },
  { id: "1234567892", title: "Apfelsaft", tags: {"category": "drink", "subcategory": "fruit"}, resultCount: 12, relevanceScore: 0.75 },
  { id: "1234567892", title: "Karotte", tags: {"category": "vegetables", "subcategory": "tuber"}, resultCount: 12, relevanceScore: 0.7 },
  { id: "1234567892", title: "Karotte", tags: {"category": "vegetables", "subcategory": "tuber"}, resultCount: 12, relevanceScore: 0.6 },
  { id: "1234567892", title: "Karotte", tags: {"category": "vegetables", "subcategory": "tuber"}, resultCount: 12, relevanceScore: 0.5 },
  { id: "1234567892", title: "Karotte", tags: {"category": "vegetables", "subcategory": "tuber"}, resultCount: 12, relevanceScore: 0.4 },
  { id: "1234567892", title: "Karotte", tags: {"category": "vegetables", "subcategory": "tuber"}, resultCount: 12, relevanceScore: 0.3 },
  { id: "1234567892", title: "Karotte", tags: {"category": "vegetables", "subcategory": "tuber"}, resultCount: 12, relevanceScore: 0.2 },
  { id: "1234567892", title: "Karotte", tags: {"category": "vegetables", "subcategory": "tuber"}, resultCount: 12, relevanceScore: 0.1 },
  { id: "1234567892", title: "Karotte", tags: {"category": "vegetables", "subcategory": "tuber"}, resultCount: 12, relevanceScore: 0.0 },
]


@Component({
  selector: 'rqms-relevance-cases-overview',
  templateUrl: './relevance-cases-overview.component.html',
  styleUrls: ['./relevance-cases-overview.component.scss']
})
export class RelevanceCasesOverviewComponent implements OnInit {

  public cases: RelevanceCase[] = DATA_SOURCE

  constructor() { }

  ngOnInit(): void {
  }

  getTags(relevanceCase: RelevanceCase): {key: string, value: string}[] {
    return Object.entries(relevanceCase.tags)
    .map(([key, value])=>{
      return {"key": key, "value": value}
    });
  }

  filter(target: EventTarget | null){
    if (target === null || !('value' in target)){
      return
    }
    const filter = (target as HTMLInputElement).value.toLowerCase();
    if (filter === null || filter === ""){
      this.cases = DATA_SOURCE
      return
    }
    this.cases = DATA_SOURCE.filter((item: RelevanceCase) => {
      if (item.title.toLowerCase().includes(filter)){
        return true;
      }
      for (const [key, value] of Object.entries(item.tags)){
        if (key.toLowerCase().includes(filter) || value.toLowerCase().includes(filter)){
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
