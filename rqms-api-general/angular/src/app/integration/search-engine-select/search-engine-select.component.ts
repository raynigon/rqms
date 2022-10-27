import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { SearchEngine } from '../model/search-engine';
import { SearchEngineClientService } from '../search-engine-client/search-engine-client.service';

function first<T>(data: T[], filter: (arg0: T) => boolean): T | null {
  const remaining: T[] = data.filter(filter);
  if (remaining.length > 0) {
    return remaining[0];
  }
  return null;
}

@Component({
  selector: 'rqms-search-engine-select',
  templateUrl: './search-engine-select.component.html',
  styleUrls: ['./search-engine-select.component.scss']
})
export class SearchEngineSelectComponent implements OnInit {

  public searchEngines: SearchEngine[] = [];
  public searchEngine: SearchEngine = { name: "default", labels: {} };

  @Output("change")
  public change: EventEmitter<SearchEngine> = new EventEmitter();

  constructor(private client: SearchEngineClientService) { }

  async ngOnInit() {
    this.searchEngines = await this.client.list();
    const filter = (it: SearchEngine) => Object.keys(it.labels).includes("default") && it.labels["default"] === "true";
    let defaultSearchEngine = first<any>(this.searchEngines, filter);
    if (defaultSearchEngine !== null) {
      this.searchEngine = defaultSearchEngine;
    } else if (this.searchEngines.length > 0) {
      this.searchEngine = this.searchEngines[0];
    } else {
      this.searchEngine = { name: "default", labels: {} };
    }
    this.change.emit(this.searchEngine);
  }

  update(event: Event) {
    event.cancelBubble = true;
    this.searchEngine = (event as any).value;
    this.change.emit(this.searchEngine);
  }

}
