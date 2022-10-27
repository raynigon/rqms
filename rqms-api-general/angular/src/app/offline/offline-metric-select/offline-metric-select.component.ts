import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { OfflineMetricsClientService } from '../offline-metrics-client/offline-metrics-client.service';

@Component({
  selector: 'rqms-offline-metric-select',
  templateUrl: './offline-metric-select.component.html',
  styleUrls: ['./offline-metric-select.component.scss']
})
export class OfflineMetricSelectComponent implements OnInit {

  public metricTypes: string[] = [];
  public metric: string  = "default";

  @Output("change")
  public change: EventEmitter<string> = new EventEmitter();

  constructor(private client: OfflineMetricsClientService) { }

  async ngOnInit() {
    this.metricTypes = await this.client.list();
    if (this.metricTypes.length > 0) {
      this.metric = this.metricTypes[0];
    } else {
      this.metric = "default";
    }
    this.change.emit(this.metric);
  }

  update(event: Event) {
    event.cancelBubble = true;
    this.metric = (event as any).value;
    this.change.emit(this.metric);
  }

}
