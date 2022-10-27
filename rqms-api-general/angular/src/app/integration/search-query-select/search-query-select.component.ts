import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { SearchQueryClientService } from '../search-query-client/search-query-client.service';

@Component({
  selector: 'rqms-search-query-select',
  templateUrl: './search-query-select.component.html',
  styleUrls: ['./search-query-select.component.scss']
})
export class SearchQuerySelectComponent implements OnInit {

  public searchQueries: string[] = [];
  public searchQuery: string = "default";

  @Output("change")
  public change: EventEmitter<string> = new EventEmitter();

  constructor(private client: SearchQueryClientService) { }

  async ngOnInit() {
    this.searchQueries = await this.client.list();
    if (this.searchQueries.length > 0) {
      this.searchQuery = this.searchQueries[0];
    } else {
      this.searchQuery = "default";
    }
    this.change.emit(this.searchQuery);
  }

  update(event: Event) {
    event.cancelBubble = true;
    this.searchQuery = (event as any).value;
    this.change.emit(this.searchQuery);
  }

}
