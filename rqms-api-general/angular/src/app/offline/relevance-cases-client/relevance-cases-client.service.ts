import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {firstValueFrom} from 'rxjs';
import {RelevanceCaseListItem} from '../model/relevance-case';

@Injectable({
  providedIn: 'root'
})
export class RelevanceCasesClientService {

  constructor(private http: HttpClient) {
  }


  async listRelevanceCases(): Promise<RelevanceCaseListItem[]> {
    const observable = this.http.get("/api/v1/offline/relevance-cases");
    return (await firstValueFrom(observable)) as RelevanceCaseListItem[];
  }
}
