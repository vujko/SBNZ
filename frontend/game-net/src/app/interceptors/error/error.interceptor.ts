import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { MessageService } from 'primeng/api';

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {

  constructor(private messageService: MessageService) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    return next.handle(request)
    .pipe(catchError((error) => {
      console.log('error is intercept')
      console.error(error);

      let errorMessage;
      if (typeof error.error === 'string') { errorMessage = error.error; }
            else if (error.error == null) { errorMessage = error.message; }
            else if (error.error instanceof Array) { errorMessage = error.error[0].defaultMessage; }
            else { errorMessage = error.error.message; }

      this.messageService.add({ severity: 'error', summary: 'Error!', detail: errorMessage})
      return throwError(error.message);
    }));
  }
}