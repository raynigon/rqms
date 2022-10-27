import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {firstValueFrom} from 'rxjs';
import { SearchEngine } from '../model/search-engine';

@Injectable({
  providedIn: 'root'
})
export class SearchEngineClientService {

  constructor(private http: HttpClient) {
  }

  async list(): Promise<SearchEngine[]> {
    const observable = this.http.get('/api/v1/integration/search-engines')
    return (await firstValueFrom(observable)) as (SearchEngine[]);
  }

  async search(searchEngine: string, searchTerm: String): Promise<any[]> {
    const observable = this.http.post(`/api/v1/integration/search-engines/${searchEngine}/search`, {
      type: 'default-basic-query',
      searchTerm: searchTerm,
      parameters: {}
    })
    return (await firstValueFrom(observable)) as (any[]);
  }
}
