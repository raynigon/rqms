import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { SearchQuery } from '../model/search-query';

@Component({
  selector: 'rqms-search-query-editor',
  templateUrl: './search-query-editor.component.html',
  styleUrls: ['./search-query-editor.component.scss']
})
export class SearchQueryEditorComponent implements OnInit {

  @Input("query")
  public query: SearchQuery = {type: "default", searchTerm: "", parameters: {}}

  @Output("queryChanged")
  public queryChanged: EventEmitter<SearchQuery> = new EventEmitter();

  public parameterView: boolean = false;

  constructor() { }

  ngOnInit(): void {
  }

  getQueryParameters(): { key: String, value: String }[] {
    const result: { key: String, value: String }[] = [];
    const params = this.query.parameters;
    for (let key in params) {
      result.push({"key": key, value: params[key]})
    }
    return result;
  }

  updateType(value: string){
    this.query.type = value;
    this.queryChanged.emit(this.query);
  }

  updateSearchTerm(value: string){
    this.query.searchTerm = value;
    this.queryChanged.emit(this.query);
  }

  updateParameters(value: any){
    this.query.parameters = value;
    this.queryChanged.emit(this.query);
  }
}
