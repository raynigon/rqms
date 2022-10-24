import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {firstValueFrom} from 'rxjs';
import {LabelFilter} from '../model/label-filter';

@Injectable({
  providedIn: 'root'
})
export class RelevanceTestRunsClientService {

  constructor(private http: HttpClient) {

  }

  //

  async createTestRun(searchEngine: string, labelFilter: LabelFilter, description: string) {
    const observable = this.http.post("/api/v1/offline/test-runs", {
      searchEngine: searchEngine,
      filter: labelFilter,
      description: description
    })
    await firstValueFrom(observable);
  }

  async listTestRuns(): Promise<any[]> {
    const observable = this.http.get("/api/v1/offline/test-runs")
    return (await firstValueFrom(observable)) as any[];
  }
}
