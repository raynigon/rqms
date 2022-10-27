import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { LabelFilter } from '../model/label-filter';

@Component({
  selector: 'rqms-label-filter',
  templateUrl: './label-filter.component.html',
  styleUrls: ['./label-filter.component.scss']
})
export class LabelFilterComponent implements OnInit {

  @Input("filter")
  public filter: LabelFilter = { operator: "AND", conditions: [{ comparison: 'ALWAYS', key: '', value: '' }] }

  constructor() { }

  ngOnInit(): void {
  }
}
