import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, throwError } from 'rxjs';
import { environment } from '../../../environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { LoginDTO } from './model';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  url:String = `${environment.apiUrl}/api/auth`

  private currentUserSubject: BehaviorSubject<LoginDTO>;
  public currentUser: Observable<LoginDTO>;

  constructor(
      private http: HttpClient,
      private router: Router
    ) { 
      this.currentUserSubject = new BehaviorSubject<LoginDTO>(this.getUserInfo());
      this.currentUser = this.currentUserSubject.asObservable();
    }

  changeCurrentUser(user:any) {
    localStorage.setItem('currentUser', JSON.stringify(user));
    this.currentUserSubject.next(this.getUserInfo());
  }

  register(request: any): Observable<any> {
    const registerUrl = this.url + '/register';
    return this.http.post<any>(registerUrl, request);
  }

  
  login(request: any): Observable<any> {
    const loginUrl = this.url + '/login';
    return this.http.post(loginUrl, request);
  }

  getUserInfo() {
    if(this.isLogedIn()) {
      let userInfo:LoginDTO = JSON.parse(localStorage.getItem('currentUser'));
      return userInfo;
    }
    return null;
  }

  isLogedIn():boolean {
    return   localStorage.getItem('currentUser') !== null;
  }

  logout() {
    localStorage.removeItem('currentUser');
    this.currentUserSubject.next(null);
    this.router.navigate(['']);
  }

  changePassword(password: String): Observable<any> {
    const loginUrl = this.url + '/change-password';
    return this.http.put(loginUrl, password);
  }
}
