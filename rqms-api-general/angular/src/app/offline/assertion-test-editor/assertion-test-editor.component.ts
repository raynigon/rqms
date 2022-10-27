import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { mergeMap, of } from 'rxjs';
import { AssertionTestsClientService } from '../assertion-tests-client/assertion-tests-client.service';
import { AssertionTest } from '../model/assertion-test';

@Component({
  selector: 'rqms-assertion-test-editor',
  templateUrl: './assertion-test-editor.component.html',
  styleUrls: ['./assertion-test-editor.component.scss']
})
export class AssertionTestEditorComponent implements OnInit {


  public createMode: boolean = false;
  public assertionTest: AssertionTest = this.createNewAssertionTest();

  public loading: boolean = false;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private client: AssertionTestsClientService
  ) {
  }

  ngOnInit(): void {
    this.loading = true;
    this.route.params.pipe(mergeMap(params => {
      const testId = params['id'];
      if (testId === "new") {
        this.createMode = true;
        return of(this.createNewAssertionTest())
      }
      this.createMode = false;
      return this.client.getAssertionTest(testId)
    })).subscribe(assertionTest => {
      this.assertionTest = assertionTest;
      this.loading = false;
    })
  }

  public isValid(): boolean {
    if (this.assertionTest.query.type === '') {
      return false;
    }
    if (this.assertionTest.query.searchTerm === '') {
      return false;
    }
    if (this.assertionTest.conditions.length < 1) {
      return false;
    }
    if (this.assertionTest.conditions.some(it => it.code === '')) {
      return false;
    }
    return true;
  }

  public async save() {
    if (this.loading) {
      return;
    }
    this.loading = true;
    if (this.createMode) {
      this.assertionTest = await this.client.createAssertionTest(this.assertionTest);
      this.createMode = false;
    } else {
      this.assertionTest = await this.client.updateAssertionTest(this.assertionTest);
      this.createMode = false;
    }
    this.loading = false;
  }

  public async delete() {
    if (!confirm("Bist du dir sicher, dass du diesen Assertion Test löschen willst?")) {
      return;
    }
    if (this.loading) {
      return;
    }
    this.loading = true;
    await this.client.deleteAssertionTest(this.assertionTest.id);
    this.loading = false;
    this.router.navigateByUrl("/offline/relevance-cases")
  }

  public removeCondition(condition: any) {
    const index = this.assertionTest.conditions.indexOf(condition);
    if (index < 0) {
      return;
    }
    this.assertionTest.conditions.splice(index, 1)
  }

  private createNewAssertionTest(): AssertionTest {
    return {
      "id": "",
      "query": {
        "type": "default-basic-query",
        "searchTerm": "",
        "parameters": {}
      },
      "conditions": []
    }
  }
}
