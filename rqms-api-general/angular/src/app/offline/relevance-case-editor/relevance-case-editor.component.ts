import {CdkDragDrop, moveItemInArray} from '@angular/cdk/drag-drop';
import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {mergeMap, of} from 'rxjs';
import {SearchEngineClientService} from 'src/app/integration/search-engine-client/search-engine-client.service';
import {RelevanceCaseDto} from '../model/relevance-case';
import {RelevanceCasesClientService} from '../relevance-cases-client/relevance-cases-client.service';

@Component({
  selector: 'rqms-relevance-case-editor',
  templateUrl: './relevance-case-editor.component.html',
  styleUrls: ['./relevance-case-editor.component.scss']
})
export class RelevanceCaseEditorComponent implements OnInit {

  public createMode: boolean = false;
  public searchEngine: string = "default";
  public relevanceCase: RelevanceCaseDto = this.createNewRelevanceCase();
  public searchTerm: string = "";
  public searchResults: any[] = [];

  public loading: boolean = false;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private client: RelevanceCasesClientService,
    private searchEngineClient: SearchEngineClientService
  ) {
  }

  ngOnInit(): void {
    this.loading = true;
    this.search("");
    this.route.params.pipe(mergeMap(params => {
      const caseId = params['id'];
      if (caseId === "new") {
        this.createMode = true;
        return of(this.createNewRelevanceCase())
      }
      this.createMode = false;
      return this.client.getRelevanceCaseById(caseId)
    })).subscribe(relevanceCase => {
      this.relevanceCase = relevanceCase;
      this.search(relevanceCase.query.searchTerm);
      this.loading = false;
    })
  }

  public isValid(): boolean {
    if (this.relevanceCase.name === "") {
      return false;
    }
    if (this.relevanceCase.query.type === "") {
      return false;
    }
    if (this.relevanceCase.query.searchTerm === "") {
      return false;
    }
    if (this.relevanceCase.metric.name === "") {
      return false;
    }
    if (this.relevanceCase.metric.cutoff < -1) {
      return false;
    }
    if (this.relevanceCase.metric.cutoff > 100) {
      return false;
    }
    if (this.relevanceCase.results.length < 1) {
      return false;
    }
    return true;
  }

  async search(searchTerm: string) {
    this.loading = true;
    this.searchTerm = searchTerm;
    const results = await this.searchEngineClient.search(this.searchEngine, searchTerm);
    this.searchResults = results.filter(result => !this.relevanceCase.results.some(exp => exp.documentId === result.documentId))
    this.loading = false;
  }

  addLabel(key: string, value: string) {
    console.log(key, value)
    this.relevanceCase.labels = {...this.relevanceCase.labels, [key]: value}
  }

  createNewRelevanceCase(): RelevanceCaseDto {
    return {
      "id": "",
      "name": "",
      "labels": {},
      "query": {
        "type": "default-basic-query",
        "searchTerm": "",
        "parameters": {}
      },
      "metric": {
        "name": "default-precision-at-k",
        "cutoff": 10
      },
      "results": []
    }
  }

  drop(event: CdkDragDrop<any[]>) {
    if (event.container == event.previousContainer && event.container.data == this.relevanceCase.results) {
      // TODO sort in container
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
    } else if (event.container != event.previousContainer && event.container.data == this.relevanceCase.results) {
      const result = this.searchResults[event.previousIndex];
      if (this.relevanceCase.results.some(it => it.documentId === result.documentId)) {
        // Item already exists
        return;
      }
      const expectedResult = {...result, relevance: 0.0};
      this.relevanceCase.results.splice(event.currentIndex, 0, expectedResult)
    } else if (event.container != event.previousContainer && event.container.data != this.relevanceCase.results) {
      this.relevanceCase.results.splice(event.previousIndex, 1)
    }

    this.search(this.searchTerm);
  }

  async save() {
    if (this.loading) {
      return;
    }
    this.loading = true;
    if (this.createMode) {
      this.relevanceCase = await this.client.createRelevanceCase(this.relevanceCase);
      this.createMode = false;
    } else {
      this.relevanceCase = await this.client.updateRelevanceCase(this.relevanceCase);
      this.createMode = false;
    }
    this.loading = false;
  }

  async delete() {
    if (!confirm("Bist du dir sicher, dass du diesen Relevance Case löschen willst?")) {
      return;
    }
    if (this.loading) {
      return;
    }
    this.loading = true;
    await this.client.deleteRelevanceCase(this.relevanceCase.id);
    this.loading = false;
    this.router.navigateByUrl("/offline/relevance-cases")
  }
}
