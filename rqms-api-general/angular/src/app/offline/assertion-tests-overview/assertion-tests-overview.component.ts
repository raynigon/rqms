import { Component, OnInit } from '@angular/core';
import { AssertionTestsClientService } from '../assertion-tests-client/assertion-tests-client.service';
import { AssertionTest } from '../model/assertion-test';

@Component({
  selector: 'rqms-assertion-tests-overview',
  templateUrl: './assertion-tests-overview.component.html',
  styleUrls: ['./assertion-tests-overview.component.scss']
})
export class AssertionTestsOverviewComponent implements OnInit {

  public assertionTests: AssertionTest[] = [];

  constructor(private client: AssertionTestsClientService) { }

  async ngOnInit(){
    this.assertionTests = await this.client.list()
  }

}
