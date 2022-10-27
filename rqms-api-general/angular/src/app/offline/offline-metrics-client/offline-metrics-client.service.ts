import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { firstValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OfflineMetricsClientService {


  constructor(private http: HttpClient) {
  }

  async list(): Promise<string[]> {
    const observable = this.http.get('/api/v1/offline/metrics')
    return (await firstValueFrom(observable)) as (string[]);
  }
}
