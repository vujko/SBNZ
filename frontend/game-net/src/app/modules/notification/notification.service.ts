import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {
  url:String = `${environment.apiUrl}/api/notification`

  constructor(
    private http: HttpClient,
  ) { }

  getNotifications(): Observable<any> {
    const recommendUrl = `${this.url}`;
    return this.http.get<any>(recommendUrl);
  }

}
