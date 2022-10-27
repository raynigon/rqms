import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { firstValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SearchQueryClientService {

  constructor(private http: HttpClient) {
  }

  async list(): Promise<string[]> {
    const observable = this.http.get('/api/v1/integration/search-queries')
    return (await firstValueFrom(observable)) as (string[]);
  }

}
