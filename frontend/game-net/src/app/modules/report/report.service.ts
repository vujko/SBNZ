import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ReportService {
  url:String = `${environment.apiUrl}/api/report`

  constructor(
    private http: HttpClient,
  ) { }

  getReport(): Observable<any> {
    const recommendUrl = `${this.url}`;
    return this.http.get<any>(recommendUrl);
  }
}
