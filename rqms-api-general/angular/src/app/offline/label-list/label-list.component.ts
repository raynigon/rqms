import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {MatChipEvent} from '@angular/material/chips';

interface Labels {
  [key: string]: string
}

interface Label {
  key: string
  value: string
  backgroundColor: string
  borderColor: string
  textColor: string
}

interface LabelsChangeEvent {
  value: Labels
}

@Component({
  selector: 'rqms-label-list',
  templateUrl: './label-list.component.html',
  styleUrls: ['./label-list.component.scss']
})
export class LabelListComponent implements OnInit, OnChanges {

  @Input("labels")
  public labels: Labels = {}

  @Input("maxLabels")
  public maxLabels: number = 10

  @Input("removable")
  public removable: boolean = false

  @Output("change")
  public change: EventEmitter<LabelsChangeEvent> = new EventEmitter<LabelsChangeEvent>();

  public labelList: Label[] = [];

  constructor() {
  }

  ngOnInit(): void {
    this.update()
  }

  ngOnChanges(changes: SimpleChanges): void {
    console.log("Something changed", changes)
    if ("labels" in changes || "maxLabels" in changes) {
      this.update()
    }
  }

  remove(event: MatChipEvent) {
    const clone = JSON.parse(JSON.stringify(this.labels))
    const key = event.chip.value.key
    delete clone[key]
    this.change.emit({
      value: clone
    })
  }

  update() {
    this.labelList = Object.entries(this.labels)
      .slice(0, this.maxLabels)
      .filter(([key, _]) => !key.startsWith("."))
      .map(([key, value]) => {
        const label: Label = {
          key: key,
          value: value,
          backgroundColor: this.hashColor(key, "background"),
          borderColor: this.hashColor(key, "border"),
          textColor: this.hashColor(key, "text"),
        };
        return label;
      });
  }

  hashColor(key: string, type: "background" | "border" | "text"): string {
    const hash = this.djb2(key);
    let r = (hash & 0xFF0000) >> 16;
    let g = (hash & 0x00FF00) >> 8;
    let b = hash & 0x0000FF;
    let a = type === "border" ? 1.0 : 0.5;
    if (type === "text") {
      if ((r + g + b) < 200) {
        return "#FFFFFF"
      }
      return "#000000"
    }
    return `rgba(${r}, ${g}, ${b}, ${a})`
  }

  djb2(value: string) {
    var hash = 5381;
    for (var i = 0; i < value.length; i++) {
      hash = ((hash << 5) + hash) + value.charCodeAt(i); /* hash * 33 + c */
    }
    return hash;
  }
}
