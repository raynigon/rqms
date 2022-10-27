import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { firstValueFrom, map, Observable } from 'rxjs';
import { AssertionTest } from '../model/assertion-test';

@Injectable({
  providedIn: 'root'
})
export class AssertionTestsClientService {

  constructor(private http: HttpClient) {
  }

  async list(): Promise<AssertionTest[]> {
    const observable = this.http.get('/api/v1/offline/assertion-tests')
    return (await firstValueFrom(observable)) as (AssertionTest[]);
  }

  getAssertionTest(testId: string): Observable<AssertionTest> {
    return this.http.get(`/api/v1/offline/assertion-tests/${testId}`).pipe(map(it=>it as AssertionTest))
  }

  async createAssertionTest(assertionTest: AssertionTest): Promise<AssertionTest> {
    const observable = this.http.post(`/api/v1/offline/assertion-tests`, assertionTest).pipe(map(it=>it as AssertionTest))
    return await firstValueFrom(observable);
  }

  async updateAssertionTest(assertionTest: AssertionTest): Promise<AssertionTest> {
    const observable = this.http.put(`/api/v1/offline/assertion-tests/${assertionTest.id}`, assertionTest).pipe(map(it=>it as AssertionTest))
    return await firstValueFrom(observable);
  }

  async deleteAssertionTest(testId: string) {
    return await firstValueFrom(this.http.delete(`/api/v1/offline/assertion-tests/${testId}`))
  }
}
