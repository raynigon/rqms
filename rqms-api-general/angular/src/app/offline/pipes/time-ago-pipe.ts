import {AsyncPipe} from "@angular/common";
import {ChangeDetectorRef, Pipe} from "@angular/core";
import {map, Observable, timer} from "rxjs";

@Pipe({
  name: 'timeAgo',
  pure: false
})
export class TimeAgoPipe extends AsyncPipe {
  private value: Date | null = null;
  private timer: Observable<string> | null = null;

  constructor(ref: ChangeDetectorRef) {
    super(ref);
  }

  override transform(obj: any): any {
    if (!this.timer) {
      this.timer = this.getObservable();
    }
    if (typeof obj === 'string') {
      this.value = new Date(obj);
      return super.transform(this.timer);
    }
    if (obj instanceof Date) {
      this.value = obj;
      return super.transform(this.timer);
    }

    return super.transform(obj);
  }

  private getObservable() {
    return timer(0, 1000).pipe(map(() => {
      var result: string;
      // current time
      let now = new Date().getTime();

      // time since message was sent in seconds
      let delta = (now - this.value!!.getTime()) / 1000;

      // format string
      if (delta < 10) {
        result = 'jetzt';
      } else if (delta < 60) { // sent in last minute
        result = 'vor ' + Math.floor(delta) + ' Sekunden';
      } else if (delta < 3600) { // sent in last hour
        result = 'vor ' + Math.floor(delta / 60) + ' Minuten';
      } else if (delta < 86400) { // sent on last day
        result = 'vor ' + Math.floor(delta / 3600) + ' Stunden';
      } else if (delta < 604800) { // sent more than one day ago
        result = 'vor ' + Math.floor(delta / 86400) + ' Tagen';
      } else {
        result = this.value!!.toLocaleDateString();
      }
      return result;
    }));
  };
}
