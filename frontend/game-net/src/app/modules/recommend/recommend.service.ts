import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RecommendService {

  url:String = `${environment.apiUrl}/api/game`

  constructor(
    private http: HttpClient,
  ) { }

  recommend(request: any): Observable<any> {
    const recommendUrl = `${this.url}/recommend`;
    return this.http.post<any>(recommendUrl, request);
  }
}
