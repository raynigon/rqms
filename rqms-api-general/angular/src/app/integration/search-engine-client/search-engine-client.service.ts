import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {firstValueFrom} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SearchEngineClientService {

  constructor(private http: HttpClient) {
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
