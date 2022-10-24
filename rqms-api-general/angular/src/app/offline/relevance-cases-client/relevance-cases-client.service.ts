import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {firstValueFrom, map, Observable} from 'rxjs';
import {RelevanceCaseDto, RelevanceCaseListItem} from '../model/relevance-case';

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

  getRelevanceCaseById(id: string): Observable<RelevanceCaseDto> {
    return this.http.get(`/api/v1/offline/relevance-cases/${id}`)
      .pipe(map(it => it as RelevanceCaseDto));
  }

  async createRelevanceCase(relevanceCase: RelevanceCaseDto) {
    const observable = this.http.post("/api/v1/offline/relevance-cases", relevanceCase)
    return (await firstValueFrom(observable)) as RelevanceCaseDto;
  }

  async updateRelevanceCase(relevanceCase: RelevanceCaseDto): Promise<RelevanceCaseDto> {
    const observable = this.http.put(`/api/v1/offline/relevance-cases/${relevanceCase.id}`, relevanceCase)
    return (await firstValueFrom(observable)) as RelevanceCaseDto;
  }

  async deleteRelevanceCase(id: string) {
    const observable = this.http.delete(`/api/v1/offline/relevance-cases/${id}`)
    await firstValueFrom(observable);
  }
}
