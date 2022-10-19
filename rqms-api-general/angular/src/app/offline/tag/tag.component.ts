import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'rqms-tag',
  templateUrl: './tag.component.html',
  styleUrls: ['./tag.component.scss']
})
export class TagComponent implements OnInit {

  @Input("key")
  public key: string = ""

  @Input("value")
  public value: string = ""

  constructor() { }

  ngOnInit(): void {
  }

  hashColor(type: "background" | "border" | "text"): string {
    const hash = this.djb2(this.key);
    let r = (hash & 0xFF0000) >> 16;
    let g = (hash & 0x00FF00) >> 8;
    let b = hash & 0x0000FF;
    let a = type === "border" ? 1.0 : 0.5;
    if (type === "text"){
      if ((r+g+b) < 200) {
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
