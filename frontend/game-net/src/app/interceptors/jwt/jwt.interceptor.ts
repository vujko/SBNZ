import { Injectable, Injector } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthenticationService } from 'src/app/modules/authentication/authentication.service';

@Injectable()
export class JwtInterceptor implements HttpInterceptor {

  constructor(private inj : Injector) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    let authenticationService:AuthenticationService = this.inj.get(AuthenticationService);

    if(authenticationService.isLogedIn()) {
      request = request.clone({
        setHeaders: {
          'Authorization': `Bearer ${authenticationService.getUserInfo().token}`
        }
      });
    }

    return next.handle(request);
  }
}
