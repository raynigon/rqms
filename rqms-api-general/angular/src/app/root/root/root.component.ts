import { Component, OnInit } from '@angular/core';

const NAVIGATION_STATE_ENTRY = "com.raynigon.root.sidenav.state"

@Component({
  selector: 'rqms-root',
  templateUrl: './root.component.html',
  styleUrls: ['./root.component.scss']
})
export class RootComponent implements OnInit {

  public navState: boolean


  constructor() {
    const entry = window.localStorage.getItem(NAVIGATION_STATE_ENTRY);
    if (entry !== null) {
      this.navState = (entry == "true");
    } else {
      this.navState = true;
    }
  }

  ngOnInit(): void {
  }


  toggleSideNav() {
    this.navState = !this.navState;
    window.localStorage.setItem(NAVIGATION_STATE_ENTRY, this.navState.toString());
  }
}
